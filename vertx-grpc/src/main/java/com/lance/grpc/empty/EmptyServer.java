package com.lance.grpc.empty;

import com.lance.grpc.StartApplication;
import com.lance.grpc.common.RpcConst;
import com.lance.grpc.empty.gen.Emp;
import com.lance.grpc.empty.gen.HeartbeatServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.vertx.core.AbstractVerticle;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * empty server
 *
 * @author lance
 * @date 2022/2/13 19:51
 */
@Slf4j
public class EmptyServer extends AbstractVerticle {

  public static void main(String[] args) {
    StartApplication.run(EmptyServer.class);
  }

  @Override
  public void start() {
    // The rpc service
    HeartbeatServiceGrpc.HeartbeatServiceImplBase service = new HeartbeatServiceGrpc.HeartbeatServiceImplBase() {
      @Override
      public void emptyCall(Emp.Empty request, StreamObserver<Emp.Empty> responseObserver) {
        log.info("Empty server receive empty message: {}", System.currentTimeMillis());
        responseObserver.onNext(Emp.Empty.newBuilder().build());
        responseObserver.onCompleted();
      }
    };

    // Create the server
    VertxServer rpcServer = VertxServerBuilder.forAddress(vertx, RpcConst.HOST, RpcConst.PORT)
        .addService(service)
        .build();

    // start the server
    rpcServer.start(ar -> {
      if (ar.failed()) {
        log.error("Grpc server start fail: ", ar.cause());
      } else {
        log.info("Grpc server[port: {}] start success.", rpcServer.getPort());
      }
    });
  }
}
