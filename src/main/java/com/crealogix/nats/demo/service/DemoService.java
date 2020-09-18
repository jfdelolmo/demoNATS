package com.crealogix.nats.demo.service;

import com.crealogix.nats.demo.listener.NatsErrorListener;
import com.crealogix.nats.demo.listener.NatsListener;
import com.crealogix.nats.demo.queue.DemoNatsQueue;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.nats.client.Subscription;

@Service
public class DemoService {

  private static final Log LOGGER = LogFactory.getLog(DemoService.class);
  private final DemoNatsQueue demoNatsQueue;

  public DemoService(DemoNatsQueue demoNatsQueue){
    this.demoNatsQueue = demoNatsQueue;
  }   

  public String sendMessage(String message){
    String m = "Message failed";
    try {
      demoNatsQueue.publish((message + " - " + LocalDateTime.now()).getBytes());
      m = "Message enqueued";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return m;
  }
  
  public List<String> readMessages() {
    List<String> received = new ArrayList<>();
    try {
      received = demoNatsQueue.getMessages();
    }catch (Exception e){
      e.printStackTrace();
    }
    return received;
  }
  
/*  public String sendMessageA(String message) {
    String m = "Message failed";
    try {
      Connection connection = Nats.connect(withOptions());

      connection.publish(SUBJECT, message.getBytes(StandardCharsets.UTF_8));

      connection.flush(Duration.ZERO);
      connection.close();
      m = "Message sent";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return m;
  }*/


/*
  public List<String> readMessages() {
    
    List<String> received = new ArrayList<>();
    try {
      Connection connection = Nats.connect(withOptions());

      CountDownLatch latch = new CountDownLatch(1);
      Dispatcher d = connection.createDispatcher(msg -> {
        String data = new String(msg.getData(), StandardCharsets.UTF_8);
        LOGGER.info("Message received: " + data);
        received.add(data);
        latch.countDown();
      });
      
      d.subscribe(SUBJECT);
      latch.await();*/
      
//      Subscription subscription = connection.subscribe(SUBJECT);
//      long pendingMessages = subscription.getPendingMessageCount();
//
//      LOGGER.info("Pending messages: " + pendingMessages);
//      while (pendingMessages > 0) {
//        Message message = subscription.nextMessage(Duration.ZERO);
//        received.add(new String(message.getData(), StandardCharsets.UTF_8));
//        pendingMessages = subscription.getPendingMessageCount();
//        LOGGER.info("Pending messages: " + pendingMessages);
//      }

/*
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }    

    return received;
  }
*/
}
