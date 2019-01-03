package io.imulab.astrea.service.discovery

import com.typesafe.config.Config
import io.grpc.stub.StreamObserver
import io.imulab.astrea.sdk.commons.discovery.DiscoveryGrpc
import io.imulab.astrea.sdk.commons.discovery.DiscoveryRequest
import io.imulab.astrea.sdk.commons.discovery.DiscoveryResponse
import io.imulab.astrea.sdk.discovery.toDiscoveryResponse
import io.imulab.astrea.sdk.oidc.discovery.Discovery
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.json.Json
import io.vertx.ext.healthchecks.HealthCheckHandler
import io.vertx.ext.healthchecks.Status
import io.vertx.ext.web.Router
import io.vertx.grpc.VertxServerBuilder
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class DiscoveryGrpcApi(
    private val discovery: Discovery,
    private val appConfig: Config,
    private val healthCheckHandler: HealthCheckHandler
) : AbstractVerticle() {

    override fun start() {
        val server = VertxServerBuilder
            .forPort(vertx, appConfig.getInt("service.grpcPort"))
            .addService(service)
            .build()

        Runtime.getRuntime().addShutdownHook(thread(start = false) {
            server.shutdown()
            server.awaitTermination(10, TimeUnit.SECONDS)
        })

        server.start()

        healthCheckHandler.register("discovery_grpc_api") { h ->
            if (server.isTerminated)
                h.complete(Status.KO())
            else
                h.complete(Status.OK())
        }
    }

    private val service = object : DiscoveryGrpc.DiscoveryImplBase() {
        override fun get(request: DiscoveryRequest?, responseObserver: StreamObserver<DiscoveryResponse>?) {
            responseObserver?.onNext(discovery.toDiscoveryResponse())
            responseObserver?.onCompleted()
        }
    }
}

class DiscoveryHttpApi(
    private val discovery: Discovery,
    private val appConfig: Config,
    private val healthCheckHandler: HealthCheckHandler
) : AbstractVerticle() {

    override fun start() {
        val router = Router.router(vertx).apply {
            get("/").handler { rc ->
                rc.response().putHeader("Content-Type", "application/json").end(
                    Json.encodePrettily(discovery)
                )
            }
            get("/health").handler(healthCheckHandler)
        }

        vertx.createHttpServer(HttpServerOptions().apply {
            port = appConfig.getInt("service.restPort")
        }).requestHandler(router).listen()

        healthCheckHandler.register("discovery_http_api") { h -> h.complete(Status.OK()) }
    }
}