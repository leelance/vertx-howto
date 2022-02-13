package com.lance.grpc.hello;

import com.lance.grpc.StartApplication;
import com.lance.grpc.common.RpcConst;
import com.lance.grpc.hello.gen.Hello;
import com.lance.grpc.hello.gen.VertxGreeterGrpc;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * hello server
 *
 * @author lance
 * @date 2022/2/13 20:09
 */
@Slf4j
public class HelloServer extends AbstractVerticle {

  public static void main(String[] args) {
    StartApplication.run(HelloServer.class);
  }

  @Override
  public void start() {
    // Create the server
    VertxServer server = VertxServerBuilder.forAddress(vertx, RpcConst.HOST, RpcConst.PORT)
        .addService(new VertxGreeterGrpc.GreeterVertxImplBase() {
          @Override
          public Future<Hello.HelloReply> sayHello(Hello.HelloRequest request) {
            log.info("Hello {}", request.getName());
            String message = "Thanks " + request.getName();
            return Future.succeededFuture(Hello.HelloReply.newBuilder().setMessage(message).build());
          }
        })
        .build();

    // start the server
    server.start(ar -> {
      if (ar.failed()) {
        log.error("Grpc server start fail: ", ar.cause());
      } else {
        log.info("Grpc server[port: {}] start success.", server.getPort());
      }
    });
  }
}
