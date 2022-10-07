package com.learning.wiki.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaProducerConfig {

  @Value("${kafka.bootstrap.server}")
  private String bootstrapServers;

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    // set safe producer configs (default in kafka > 3.0)
    configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
    configProps.put(ProducerConfig.ACKS_CONFIG, "all");
    /**
     * for acks=all, replication factor = M, min.insync.replicas should be a minimum of 2 so that we
     * can be sure the data is replicated on at least one more broker apart from leader.
     */
    configProps.put(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));

    // set high throughput producer configs (at the expense of a bit of latency & CPU cycles)
    configProps.put(ProducerConfig.LINGER_MS_CONFIG, "20");
    // Lingering allows to wait a bit of time before sending messages and increases the chances of
    // batching
    configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32 * 1024)); // 32 bytes
    configProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
