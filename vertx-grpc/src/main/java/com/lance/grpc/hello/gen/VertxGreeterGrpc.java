package com.lance.grpc.hello.gen;

import static com.lance.grpc.hello.gen.GreeterGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;


@javax.annotation.Generated(
    value = "by VertxGrpc generator",
    comments = "Source: hello.proto")
public final class VertxGreeterGrpc {
    private VertxGreeterGrpc() {
    }

    public static GreeterVertxStub newVertxStub(io.grpc.Channel channel) {
        return new GreeterVertxStub(channel);
    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static final class GreeterVertxStub extends io.grpc.stub.AbstractStub<GreeterVertxStub> {
        private final io.vertx.core.impl.ContextInternal ctx;
        private GreeterGrpc.GreeterStub delegateStub;

        private GreeterVertxStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = GreeterGrpc.newStub(channel);
            this.ctx = (io.vertx.core.impl.ContextInternal) io.vertx.core.Vertx.currentContext();
        }

        private GreeterVertxStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = GreeterGrpc.newStub(channel).build(channel, callOptions);
            this.ctx = (io.vertx.core.impl.ContextInternal) io.vertx.core.Vertx.currentContext();
        }

        @Override
        protected GreeterVertxStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new GreeterVertxStub(channel, callOptions);
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.vertx.core.Future<com.lance.grpc.hello.gen.Hello.HelloReply> sayHello(com.lance.grpc.hello.gen.Hello.HelloRequest request) {
            return io.vertx.grpc.stub.ClientCalls.oneToOne(ctx, request, delegateStub::sayHello);
        }

    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static abstract class GreeterVertxImplBase implements io.grpc.BindableService {
        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public GreeterVertxImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.vertx.core.Future<com.lance.grpc.hello.gen.Hello.HelloReply> sayHello(com.lance.grpc.hello.gen.Hello.HelloRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                .addMethod(
                    com.lance.grpc.hello.gen.GreeterGrpc.getSayHelloMethod(),
                    asyncUnaryCall(
                        new MethodHandlers<
                            com.lance.grpc.hello.gen.Hello.HelloRequest,
                            com.lance.grpc.hello.gen.Hello.HelloReply>(
                            this, METHODID_SAY_HELLO, compression)))
                .build();
        }
    }

    private static final int METHODID_SAY_HELLO = 0;

    private static final class MethodHandlers<Req, Resp> implements
        io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final GreeterVertxImplBase serviceImpl;
        private final int methodId;
        private final String compression;

        MethodHandlers(GreeterVertxImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO:
                    io.vertx.grpc.stub.ServerCalls.oneToOne(
                        (com.lance.grpc.hello.gen.Hello.HelloRequest) request,
                        (io.grpc.stub.StreamObserver<com.lance.grpc.hello.gen.Hello.HelloReply>) responseObserver,
                        compression,
                        serviceImpl::sayHello);
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
