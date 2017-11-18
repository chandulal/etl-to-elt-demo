package com.thoughtworks.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.mongeez.Mongeez;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;


@Configuration
public class MongoConfig {

  private static final String MONGO_DB_URL = "localhost";
  private static final String MONGO_DB_NAME = "embeded_db";

  @Bean
  public MongoTemplate mongoTemplate() throws IOException {
    EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
    mongo.setBindIp(MONGO_DB_URL);
    MongoClient mongoClient = (MongoClient) mongo.getObject();
    MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
    loadData(mongoClient);
    return mongoTemplate;
  }

  private void loadData(MongoClient mongoClient) {
    Mongeez mongeez = new Mongeez();
    String path = "mongeez.xml";
    mongeez.setFile(new ClassPathResource(path));
    mongeez.setMongo(mongoClient);
    mongeez.setDbName(MONGO_DB_NAME);
    mongeez.process();
  }
}