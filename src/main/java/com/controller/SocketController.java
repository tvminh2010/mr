package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.entity.Greeting;


@Controller
@EnableScheduling
@PropertySource(value = { "classpath:closetime.properties" })
public class SocketController {
	  @Autowired
	    private SimpMessagingTemplate template;
	
	/*  @Scheduled(cron="${lan1.time}")
	  @MessageMapping("/hello")
	    @SendTo("/topic/greetings")
	    public void greeting1() throws Exception {
	        Thread.sleep(1000); // simulated delay
	       // return new Greeting("Hello, " + message.getName() + "!");
	       // System.out.println("scheduled");
	        this.template.convertAndSend("/topic/greetings",  new Greeting("Hello"));
	    }*/
}
