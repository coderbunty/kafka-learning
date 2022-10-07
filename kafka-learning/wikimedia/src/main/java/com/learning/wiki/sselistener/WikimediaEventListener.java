package com.learning.wiki.sselistener;

import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WikimediaEventListener {

  @KafkaListener(topics = "#{'${kafka.wikimedia.topic}'}", groupId = "demo-consumer-group")
  public void listen(@Payload ConsumerRecord<String, String> consumerRecord) {
    log.info("ID: {}, Message: {}", extractId(consumerRecord.value()), consumerRecord.value());
  }

  private String extractId(String jsonMessage) {
    return JsonParser.parseString(jsonMessage)
        .getAsJsonObject()
        .get("meta")
        .getAsJsonObject()
        .get("id")
        .getAsString();
  }
}
