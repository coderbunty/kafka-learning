package com.learning.wiki.starter;

import com.learning.wiki.service.ServerSentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupExecution implements CommandLineRunner {

  private ServerSentEvent wikimediaSse;

  @Autowired
  public StartupExecution(ServerSentEvent wikimediaSse) {
    this.wikimediaSse = wikimediaSse;
  }

  @Override
  public void run(String... args) throws Exception {
    wikimediaSse.read();
  }
}
