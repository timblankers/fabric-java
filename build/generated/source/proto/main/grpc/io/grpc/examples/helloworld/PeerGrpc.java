package io.grpc.examples.helloworld;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: helloworld.proto")
public class PeerGrpc {

  private PeerGrpc() {}

  public static final String SERVICE_NAME = "helloworld.Peer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.examples.helloworld.Message,
      io.grpc.examples.helloworld.Message> METHOD_CHAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "helloworld.Peer", "Chat"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.examples.helloworld.Message.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.examples.helloworld.Message.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PeerStub newStub(io.grpc.Channel channel) {
    return new PeerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PeerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PeerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static PeerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PeerFutureStub(channel);
  }

  /**
   */
  public static abstract class PeerImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.Message> chat(
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.Message> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_CHAT, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CHAT,
            asyncBidiStreamingCall(
              new MethodHandlers<
                io.grpc.examples.helloworld.Message,
                io.grpc.examples.helloworld.Message>(
                  this, METHODID_CHAT)))
          .build();
    }
  }

  /**
   */
  public static final class PeerStub extends io.grpc.stub.AbstractStub<PeerStub> {
    private PeerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeerStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.Message> chat(
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.Message> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_CHAT, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class PeerBlockingStub extends io.grpc.stub.AbstractStub<PeerBlockingStub> {
    private PeerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeerBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class PeerFutureStub extends io.grpc.stub.AbstractStub<PeerFutureStub> {
    private PeerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeerFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CHAT = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PeerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(PeerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chat(
              (io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.Message>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_CHAT);
  }

}
