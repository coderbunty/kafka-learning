package com.meesh.app.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.meesh"})
public class Application {

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}