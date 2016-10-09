package io.grpc.fabric;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import com.google.protobuf.Timestamp;
import com.google.protobuf.ByteString;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.Instant;

public class FabricClient {
  private static final Logger logger = Logger.getLogger(FabricClient.class.getName());

  private final ManagedChannel channel;
  private final PeerGrpc.PeerBlockingStub blockingStub;
  private final PeerGrpc.PeerStub asyncStub;

  public FabricClient(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext(true)
        .build();
    blockingStub = PeerGrpc.newBlockingStub(channel);
    asyncStub = PeerGrpc.newStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  public void discover() throws Exception {
    logger.info("[server] Starting StreamObserver...");
    final CountDownLatch finishLatch = new CountDownLatch(1);
    StreamObserver<Message> requestObserver =
      asyncStub.chat(new StreamObserver<Message>() {
        @Override
        public void onNext(Message incoming) {
          info("Receiving message \"{0}\" at {1}", incoming.getType(), incoming.getTimestamp());
        }

        @Override
        public void onError(Throwable t) {
          Status status = Status.fromThrowable(t);
          logger.log(Level.WARNING, "Chat Failed: {0}", status);
          finishLatch.countDown();
        }

        @Override
        public void onCompleted() {
          logger.info("Finished Discovery");
          finishLatch.countDown();
        }
      });
    logger.info("[server] ... StreamObserver ready");

    try {
      Message[] requests = { newMessage() };

      for (Message request : requests) {
        info("Sending message \"{0}\" at {1}", request.getType(), request.getTimestamp());
        requestObserver.onNext(request);
      }
    } catch (RuntimeException e) {
      requestObserver.onError(e);
      throw e;
    }

    requestObserver.onCompleted();
    finishLatch.await(1, TimeUnit.MINUTES);
  }

  // public void discover() {
    // Iterator<Message> responses;
    // // Collection<Message> discoveries;
    // Message discovery = newMessage();
    //
    // try {
    //   // responses = blockingStub.chat(discovery);
    // } catch (StatusRuntimeException e) {
    //   logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
    //   return;
    // }
    // while (responses.hasNext()) {
    //   Object element = responses.next();
    //   logger.info("[response] " + element);
    // }
    // logger.info("Response: " + response.getType());
  // }

  private Message newMessage() {
    ByteString bytes = ByteString.copyFromUtf8("test");
    BlockchainInfo blockchainInfo =
      BlockchainInfo.newBuilder()
        .setHeight(0)
        .setCurrentBlockHash(bytes)
        .setPreviousBlockHash(bytes)
        .build();

    PeerID peerID =
      PeerID.newBuilder()
        .setName("JavaPeer")
        .build();

    ByteString bytes2 = ByteString.copyFromUtf8("insert pki id here");
    PeerEndpoint peerEndpoint =
      PeerEndpoint.newBuilder()
        .setID(peerID)
        .setAddress("localhost")
        .setType(PeerEndpoint.Type.NON_VALIDATOR)
        .setPkiID(bytes2)
        .build();

    HelloMessage helloMessage =
      HelloMessage.newBuilder()
        .setPeerEndpoint(peerEndpoint)
        .setBlockchainInfo(blockchainInfo)
        .build();

    Instant time = Instant.now();
    return Message.newBuilder()
        .setType(Message.Type.DISC_HELLO)
        .setTimestamp(Timestamp.newBuilder().setSeconds(time.getEpochSecond()))
        .setPayload(helloMessage.toByteString())
        .build();
  }

  private static void info(String msg, Object... params) {
    logger.log(Level.INFO, msg, params);
  }

  public static void main(String[] args) throws Exception {
    FabricClient client = new FabricClient("localhost", 7051);
    try {
      client.discover();
    } finally {
      client.shutdown();
    }
  }
}
