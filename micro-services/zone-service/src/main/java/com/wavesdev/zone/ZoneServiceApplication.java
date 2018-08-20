package com.wavesdev.zone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@RequestMapping("/zone")
public class ZoneServiceApplication {

  private static Logger log = LoggerFactory.getLogger(ZoneServiceApplication.class);

  @RequestMapping(value = "/greeting")
  public String greet() {
    log.info("Access /greeting");

    List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
    Random rand = new Random();

    int randomNum = rand.nextInt(greetings.size());
    return greetings.get(randomNum);
  }

  @RequestMapping(value = "/")
  public String home() {
    log.info("Access /");
    return "Hi!";
  }

  @Value("${eureka.instance.metadataMap.zone}")
  private String zone;
  @GetMapping( produces = APPLICATION_JSON_VALUE)
  public String zone() {
    return "{\"zone\"=\"" + zone + "\"}";
  }

  public static void main(String[] args) {
    SpringApplication.run(ZoneServiceApplication.class, args);
  }
}
