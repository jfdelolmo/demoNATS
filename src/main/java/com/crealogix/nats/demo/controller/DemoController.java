package com.crealogix.nats.demo.controller;

import com.crealogix.nats.demo.service.DemoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

  public final DemoService demoService;

  public DemoController(DemoService demoService) {
    this.demoService = demoService;
  }

  @PostMapping(value = "/send")
  public void sendMessage(@RequestParam(value = "message") String message) {
    System.out.println("I have to send this message: " + message);
    String s = demoService.sendMessage(message);
    System.out.println("The service says: " + s);
  }
  
  @GetMapping(value = "/read")
  public void readMessages(){
    List<String> received = demoService.readMessages();
    System.out.println("BEGIN MESSAGES TO READ");
    received.forEach(System.out::println);
    System.out.println("END MESSAGES TO READ");
  } 
}
