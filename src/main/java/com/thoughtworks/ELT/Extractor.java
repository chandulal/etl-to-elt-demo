package com.thoughtworks.ELT;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Extractor {

  private static final Logger LOGGER = LoggerFactory.getLogger(Extractor.class);

  @Autowired
  private KafkaTemplate<String, JsonNode> kafkaTemplate;

  public void send(String topic, JsonNode payload) {
    LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
    kafkaTemplate.send(topic, payload);
  }
}