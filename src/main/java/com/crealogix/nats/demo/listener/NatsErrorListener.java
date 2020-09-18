package com.crealogix.nats.demo.listener;

import java.nio.charset.StandardCharsets;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import io.nats.client.Connection;
import io.nats.client.Consumer;
import io.nats.client.ErrorListener;
import io.nats.client.Message;

@Component
public class NatsErrorListener implements ErrorListener {

  private static final Log LOGGER = LogFactory.getLog(NatsErrorListener.class);

  @Override
  public void errorOccurred(Connection conn, String error) {
    LOGGER.info("NATS connection error occurred: " + error);
  }

  @Override
  public void exceptionOccurred(Connection conn, Exception exp) {
    LOGGER.info("NATS connection exception occurred: ", exp);
  }

  @Override
  public void slowConsumerDetected(Connection conn, Consumer consumer) {
    LOGGER.info("NATS connection slow consumer detected");
  }

  @Override
  public void messageDiscarded(Connection conn, Message msg) {
    LOGGER.info("NATS connection message discarded " + msg.getSID() + " - " + new String(msg.getData(), StandardCharsets.UTF_8));
  }
}
