package com.crealogix.nats.demo.listener;

import org.springframework.stereotype.Component;

import io.nats.client.Connection;
import io.nats.client.ConnectionListener;

@Component
public class NatsListener implements ConnectionListener {
  
  @Override
  public void connectionEvent(Connection conn, Events type) {
      System.out.println("## Custom status change: " + type);
  }
}
