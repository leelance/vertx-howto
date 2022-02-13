package com.lance.grpc.empty.gen;

import static com.lance.grpc.empty.gen.HeartbeatServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;


@javax.annotation.Generated(
    value = "by VertxGrpc generator",
    comments = "Source: empty.proto")
public final class VertxHeartbeatServiceGrpc {
    private VertxHeartbeatServiceGrpc() {
    }

    public static HeartbeatServiceVertxStub newVertxStub(io.grpc.Channel channel) {
        return new HeartbeatServiceVertxStub(channel);
    }


    public static final class HeartbeatServiceVertxStub extends io.grpc.stub.AbstractStub<HeartbeatServiceVertxStub> {
        private final io.vertx.core.impl.ContextInternal ctx;
        private HeartbeatServiceGrpc.HeartbeatServiceStub delegateStub;

        private HeartbeatServiceVertxStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = HeartbeatServiceGrpc.newStub(channel);
            this.ctx = (io.vertx.core.impl.ContextInternal) io.vertx.core.Vertx.currentContext();
        }

        private HeartbeatServiceVertxStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = HeartbeatServiceGrpc.newStub(channel).build(channel, callOptions);
            this.ctx = (io.vertx.core.impl.ContextInternal) io.vertx.core.Vertx.currentContext();
        }

        @Override
        protected HeartbeatServiceVertxStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HeartbeatServiceVertxStub(channel, callOptions);
        }

        /**
         * <pre>
         *  One empty request followed by one empty response.
         * </pre>
         */
        public io.vertx.core.Future<com.lance.grpc.empty.gen.Emp.Empty> emptyCall(com.lance.grpc.empty.gen.Emp.Empty request) {
            return io.vertx.grpc.stub.ClientCalls.oneToOne(ctx, request, delegateStub::emptyCall);
        }

    }


    public static abstract class HeartbeatServiceVertxImplBase implements io.grpc.BindableService {
        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public HeartbeatServiceVertxImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        /**
         * <pre>
         *  One empty request followed by one empty response.
         * </pre>
         */
        public io.vertx.core.Future<com.lance.grpc.empty.gen.Emp.Empty> emptyCall(com.lance.grpc.empty.gen.Emp.Empty request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                .addMethod(
                    com.lance.grpc.empty.gen.HeartbeatServiceGrpc.getEmptyCallMethod(),
                    asyncUnaryCall(
                        new MethodHandlers<
                            com.lance.grpc.empty.gen.Emp.Empty,
                            com.lance.grpc.empty.gen.Emp.Empty>(
                            this, METHODID_EMPTY_CALL, compression)))
                .build();
        }
    }

    private static final int METHODID_EMPTY_CALL = 0;

    private static final class MethodHandlers<Req, Resp> implements
        io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final HeartbeatServiceVertxImplBase serviceImpl;
        private final int methodId;
        private final String compression;

        MethodHandlers(HeartbeatServiceVertxImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_EMPTY_CALL:
                    io.vertx.grpc.stub.ServerCalls.oneToOne(
                        (com.lance.grpc.empty.gen.Emp.Empty) request,
                        (io.grpc.stub.StreamObserver<com.lance.grpc.empty.gen.Emp.Empty>) responseObserver,
                        compression,
                        serviceImpl::emptyCall);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
