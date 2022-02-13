package com.lance.grpc.hello;

import com.lance.grpc.StartApplication;
import com.lance.grpc.common.RpcConst;
import com.lance.grpc.hello.gen.Hello;
import com.lance.grpc.hello.gen.VertxGreeterGrpc;
import io.grpc.ManagedChannel;
import io.vertx.core.AbstractVerticle;
import io.vertx.grpc.VertxChannelBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * hello client
 *
 * @author lance
 * @date 2022/2/13 20:17
 */
@Slf4j
public class HelloClient extends AbstractVerticle {

  public static void main(String[] args) {
    StartApplication.run(HelloClient.class);
  }

  @Override
  public void start() {
    // Create the channel
    ManagedChannel channel = VertxChannelBuilder
        .forAddress(vertx, RpcConst.HOST, RpcConst.PORT)
        .usePlaintext()
        .build();

    VertxGreeterGrpc.GreeterVertxStub stub = VertxGreeterGrpc.newVertxStub(channel);
    Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setName("Jim Green").build();

    stub.sayHello(request).onComplete(asyncResponse -> {
      if (asyncResponse.succeeded()) {
        log.info("Got the server response: {}", asyncResponse.result().getMessage());
      } else {
        log.error("Could not reach server fail: ", asyncResponse.cause());
      }
    });
  }
}
