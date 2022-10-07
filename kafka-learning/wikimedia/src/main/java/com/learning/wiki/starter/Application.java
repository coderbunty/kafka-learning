package com.learning.wiki.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.learning.wiki"})
public class Application {

  public static void main(String[] args) {
    new SpringApplication(Application.class).run(args);
  }
}
