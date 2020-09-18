package com.crealogix.nats.demo.queue;

import com.crealogix.nats.demo.listener.NatsErrorListener;
import com.crealogix.nats.demo.listener.NatsListener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.nats.client.Subscription;

@Component
public class DemoNatsQueue {

  private static final String NATS_URL = "nats://localhost:4222";
  private static final String SUBJECT = "DEMO";
  private static final String MSG_QUEUE_NAME = "DEMO_QUEUE";

  private final NatsListener natsListener;
  private final NatsErrorListener natsErrorListener;
  private Connection connection;
  private Subscription queue;

  public DemoNatsQueue(NatsListener natsListener, NatsErrorListener natsErrorListener) throws IOException, InterruptedException {
    this.natsListener = natsListener;
    this.natsErrorListener = natsErrorListener;
    initialize();
  }

  /**
   * Subscribes the connection to the MSGs queue
   */
  protected void initialize() throws IOException, InterruptedException {
    this.connection = Nats.connect(withOptions());
    this.queue = connection.subscribe(SUBJECT, MSG_QUEUE_NAME);
  }
  
  private Options withOptions() {
    return  new Options.Builder().server(NATS_URL)
//        .traceConnection()
        .connectionListener(natsListener)
        .errorListener(natsErrorListener)
        .build();
  }

  public void publish(byte[] bytes) {
    this.connection.publish(SUBJECT, bytes);
  }

  public List<String> getMessages() throws InterruptedException {
    boolean out = false;
    List<String> received = new ArrayList<>();
    do {
      Message message = this.queue.nextMessage(Duration.ofMillis(3000));
      out = null == message;
      if (!out) {
        received.add(new String(message.getData(), StandardCharsets.UTF_8));
      }

    } while (!out);
    return received;
  }
}
