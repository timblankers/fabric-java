package io.grpc.fabric;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import com.google.protobuf.Timestamp;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.Instant;

public class FabricClient {
  private static final Logger logger = Logger.getLogger(FabricClient.class.getName());

  private final ManagedChannel channel;
  private final PeerGrpc.PeerBlockingStub blockingStub;

  public FabricClient(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext(true)
        .build();
    blockingStub = PeerGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  public void discover() {
    Instant time = Instant.now();
    Message request =
        Message.newBuilder()
          .setType(Message.Type.DISC_HELLO)
          .setTimestamp(Timestamp.newBuilder().setSeconds(time.getEpochSecond()))
          .build();
    Message response;
    try {
      response = blockingStub.chat(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    logger.info("Response: " + response.getType());
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
