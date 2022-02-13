package com.lance.grpc.empty.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 *
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: empty.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HeartbeatServiceGrpc {

  private HeartbeatServiceGrpc() {
  }

  public static final String SERVICE_NAME = "com.lance.grpc.empty.gen.HeartbeatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.lance.grpc.empty.gen.Emp.Empty,
      com.lance.grpc.empty.gen.Emp.Empty> getEmptyCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EmptyCall",
      requestType = com.lance.grpc.empty.gen.Emp.Empty.class,
      responseType = com.lance.grpc.empty.gen.Emp.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.lance.grpc.empty.gen.Emp.Empty,
      com.lance.grpc.empty.gen.Emp.Empty> getEmptyCallMethod() {
    io.grpc.MethodDescriptor<com.lance.grpc.empty.gen.Emp.Empty, com.lance.grpc.empty.gen.Emp.Empty> getEmptyCallMethod;
    if ((getEmptyCallMethod = HeartbeatServiceGrpc.getEmptyCallMethod) == null) {
      synchronized (HeartbeatServiceGrpc.class) {
        if ((getEmptyCallMethod = HeartbeatServiceGrpc.getEmptyCallMethod) == null) {
          HeartbeatServiceGrpc.getEmptyCallMethod = getEmptyCallMethod =
              io.grpc.MethodDescriptor.<com.lance.grpc.empty.gen.Emp.Empty, com.lance.grpc.empty.gen.Emp.Empty>newBuilder()
                  .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                  .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EmptyCall"))
                  .setSampledToLocalTracing(true)
                  .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                      com.lance.grpc.empty.gen.Emp.Empty.getDefaultInstance()))
                  .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                      com.lance.grpc.empty.gen.Emp.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new HeartbeatServiceMethodDescriptorSupplier("EmptyCall"))
                  .build();
        }
      }
    }
    return getEmptyCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HeartbeatServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HeartbeatServiceStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<HeartbeatServiceStub>() {
          @java.lang.Override
          public HeartbeatServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HeartbeatServiceStub(channel, callOptions);
          }
        };
    return HeartbeatServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HeartbeatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HeartbeatServiceBlockingStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<HeartbeatServiceBlockingStub>() {
          @java.lang.Override
          public HeartbeatServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HeartbeatServiceBlockingStub(channel, callOptions);
          }
        };
    return HeartbeatServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HeartbeatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HeartbeatServiceFutureStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<HeartbeatServiceFutureStub>() {
          @java.lang.Override
          public HeartbeatServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HeartbeatServiceFutureStub(channel, callOptions);
          }
        };
    return HeartbeatServiceFutureStub.newStub(factory, channel);
  }

  /**
   *
   */
  public static abstract class HeartbeatServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * One empty request followed by one empty response.
     * </pre>
     */
    public void emptyCall(com.lance.grpc.empty.gen.Emp.Empty request,
                          io.grpc.stub.StreamObserver<com.lance.grpc.empty.gen.Emp.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEmptyCallMethod(), responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
              getEmptyCallMethod(),
              io.grpc.stub.ServerCalls.asyncUnaryCall(
                  new MethodHandlers<
                      com.lance.grpc.empty.gen.Emp.Empty,
                      com.lance.grpc.empty.gen.Emp.Empty>(
                      this, METHODID_EMPTY_CALL)))
          .build();
    }
  }

  /**
   *
   */
  public static final class HeartbeatServiceStub extends io.grpc.stub.AbstractAsyncStub<HeartbeatServiceStub> {
    private HeartbeatServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartbeatServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HeartbeatServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * One empty request followed by one empty response.
     * </pre>
     */
    public void emptyCall(com.lance.grpc.empty.gen.Emp.Empty request,
                          io.grpc.stub.StreamObserver<com.lance.grpc.empty.gen.Emp.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEmptyCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   *
   */
  public static final class HeartbeatServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<HeartbeatServiceBlockingStub> {
    private HeartbeatServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartbeatServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HeartbeatServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * One empty request followed by one empty response.
     * </pre>
     */
    public com.lance.grpc.empty.gen.Emp.Empty emptyCall(com.lance.grpc.empty.gen.Emp.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEmptyCallMethod(), getCallOptions(), request);
    }
  }

  /**
   *
   */
  public static final class HeartbeatServiceFutureStub extends io.grpc.stub.AbstractFutureStub<HeartbeatServiceFutureStub> {
    private HeartbeatServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartbeatServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HeartbeatServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * One empty request followed by one empty response.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.lance.grpc.empty.gen.Emp.Empty> emptyCall(
        com.lance.grpc.empty.gen.Emp.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEmptyCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EMPTY_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HeartbeatServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HeartbeatServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EMPTY_CALL:
          serviceImpl.emptyCall((com.lance.grpc.empty.gen.Emp.Empty) request,
              (io.grpc.stub.StreamObserver<com.lance.grpc.empty.gen.Emp.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HeartbeatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HeartbeatServiceBaseDescriptorSupplier() {
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.lance.grpc.empty.gen.Emp.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HeartbeatService");
    }
  }

  private static final class HeartbeatServiceFileDescriptorSupplier
      extends HeartbeatServiceBaseDescriptorSupplier {
    HeartbeatServiceFileDescriptorSupplier() {
    }
  }

  private static final class HeartbeatServiceMethodDescriptorSupplier
      extends HeartbeatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HeartbeatServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HeartbeatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HeartbeatServiceFileDescriptorSupplier())
              .addMethod(getEmptyCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
