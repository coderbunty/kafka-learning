package com.meesh.app.consumers;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoEventConsumer {

  /*
   * In this listener we have enabled manual commit, we can decide when to acknowledgment.acknowledge() or acknowledgment.nack()
   * As we are using at least once delivery semantic, if something goes wrong in the processing, then the message (from same offset) will be read again (10 times)
   * If the message is read again, we need to make sue that the processing is idempotent, i.e., the state doesn't change when processing fails
   * One more option if we can't keep processing idempotent is to catch the Exception & commit the offset & log this incident in some kind of metric where it can be tracked.
   *
   * Why acknowledgement.acknowledge() is not optional https://github.com/spring-projects/spring-kafka/discussions/2519
   *
   * We can also use set concurrency to have multiple consumers consume data from a topic instead of using a consumer group,
   * https://stackoverflow.com/questions/75143480/to-achieve-concurrency-in-kafka-listener-how-are-defining-consumer-groups-using/75148581#75148581
   */
  @KafkaListener(
      groupId = "demo-consumer-group",
      topics = {"demo_topic"},
      containerFactory = "demoListenerFactory")
  public void consumeDemoEvent(
      @Payload ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
    try {
      String key = consumerRecord.key();
      String value = consumerRecord.value();
      doSomeDbOperation();
      processDemoEvent(value);
    } catch (Exception e) {
      log.error("Error while processing record: ", e);
    } finally {
      acknowledgment.acknowledge();
    }
  }

  private void doSomeDbOperation() {
    log.info("DB operation in progress");
  }

  private void processDemoEvent(String value) {
    log.info("Processing demo event");
    throw new RuntimeException("Something went wrong");
  }
}
