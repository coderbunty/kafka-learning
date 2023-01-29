package com.meesh.app.producers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class LocationCoordinateEventProducer {

  @Autowired private KafkaTemplate<String, String> kafkaTemplate;

  private String topic = "location_topic";

  public void publishEvent(String message) {
    ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
    future.addCallback(
        new ListenableFutureCallback<SendResult<String, String>>() {
          @Override
          public void onFailure(Throwable e) {
            log.error("Failed to send data to kafka", e);
          }

          @Override
          public void onSuccess(SendResult<String, String> result) {
            log.info(
                "Sent message: "
                    + result.getProducerRecord().value()
                    + " to topic: "
                    + result.getRecordMetadata().topic()
                    + ", partition: "
                    + result.getRecordMetadata().partition()
                    + ", offset: "
                    + result.getRecordMetadata().offset());
          }
        });
  }
}
