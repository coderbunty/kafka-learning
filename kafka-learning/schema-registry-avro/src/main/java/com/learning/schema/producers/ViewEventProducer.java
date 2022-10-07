package com.learning.schema.producers;

import com.learning.schema.avro.ViewEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Slf4j
@Service
public class ViewEventProducer {

  @Autowired private KafkaTemplate kafkaTemplate;

  @Value("${kafka.view.event.topic}")
  private String topic;

  public void send(ViewEvent viewEvent) {
    ListenableFuture<SendResult<String, ViewEvent>> future = kafkaTemplate.send(topic, viewEvent);
    future.addCallback(
        new ListenableFutureCallback<>() {
          @Override
          public void onFailure(Throwable e) {
            log.error("Failed to send data to kafka brokers", e);
          }

          @Override
          public void onSuccess(SendResult<String, ViewEvent> result) {
            log.info("Successfully sent view event record to kafka brokers");
          }
        });
  }

  public void sendBatch(List<ViewEvent> viewEvents) {
    viewEvents.forEach(this::send);
  }
}
