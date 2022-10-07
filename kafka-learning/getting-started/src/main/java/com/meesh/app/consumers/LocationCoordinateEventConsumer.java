package com.meesh.app.consumers;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocationCoordinateEventConsumer {

  @KafkaListener(
      groupId = "demo-consumer-group",
      topics = {"demo_topic"})
  public void consumerEvent(@Payload ConsumerRecord<String, String> consumerRecord) {
    // Thread.sleep(5000);
    log.info("Received message: " + consumerRecord.value());
  }
}
