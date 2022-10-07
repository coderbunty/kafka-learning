package com.learning.wiki.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WikimediaChangeHandler implements EventHandler {

  @Autowired private KafkaTemplate<String, String> kafkaTemplate;

  @Value("${kafka.wikimedia.topic}")
  private String topic;

  @Override
  public void onOpen() throws Exception {}

  @Override
  public void onClosed() throws Exception {}

  @Override
  public void onMessage(String event, MessageEvent messageEvent) throws Exception {
    // log.info(messageEvent.getData());
    kafkaTemplate.send(new ProducerRecord<>(topic, messageEvent.getData()));
  }

  @Override
  public void onComment(String s) throws Exception {}

  @Override
  public void onError(Throwable throwable) {
    log.error("Error in streaming data", throwable);
  }
}
