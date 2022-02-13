package com.lance.grpc.empty;

import com.lance.grpc.StartApplication;
import com.lance.grpc.common.RpcConst;
import com.lance.grpc.empty.gen.Emp;
import com.lance.grpc.empty.gen.VertxHeartbeatServiceGrpc;
import io.grpc.ManagedChannel;
import io.vertx.core.AbstractVerticle;
import io.vertx.grpc.VertxChannelBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * empty client
 *
 * @author lance
 * @date 2022/2/13 19:51
 */
@Slf4j
public class EmptyClient extends AbstractVerticle {

  public static void main(String[] args) {
    StartApplication.run(EmptyClient.class);
  }

  @Override
  public void start() {
    // Create the channel
    ManagedChannel channel = VertxChannelBuilder
        .forAddress(vertx, RpcConst.HOST, RpcConst.PORT)
        .usePlaintext()
        .build();

    // Get a stub to use for interacting with the remote service
    VertxHeartbeatServiceGrpc.HeartbeatServiceVertxStub stub = VertxHeartbeatServiceGrpc.newVertxStub(channel);

    // Make a request
    Emp.Empty request = Emp.Empty.newBuilder().build();

    // Call the remote service
    stub.emptyCall(request).onComplete(ar -> {
      if (ar.succeeded()) {
        log.info("Got the server response.");
      } else {
        log.error("Could not reach server fail: ", ar.cause());
      }
    });
  }
}
