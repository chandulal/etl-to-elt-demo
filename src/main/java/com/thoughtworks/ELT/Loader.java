package com.thoughtworks.ELT;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.*;
import scala.Tuple2;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Loader {
  public static void main(String[] args) throws InterruptedException {
    Map<String, Object> kafkaParams = new HashMap<>();
    kafkaParams.put("bootstrap.servers", "localhost:9092");
    kafkaParams.put("key.deserializer", StringDeserializer.class);
    kafkaParams.put("value.deserializer", "org.apache.kafka.connect.json.JsonDeserializer");
    kafkaParams.put("group.id", "1");
    kafkaParams.put("auto.offset.reset", "latest");
    kafkaParams.put("enable.auto.commit", false);

    SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("Loader");
    JavaStreamingContext streamingContext = new JavaStreamingContext(sparkConf, Durations.seconds(1));

    Collection<String> topics = Collections.singletonList("helloworld.t");


    JavaInputDStream<ConsumerRecord<String, String>> stream = KafkaUtils.createDirectStream(
        streamingContext,
        LocationStrategies.PreferConsistent(),
        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
    );

    stream.foreachRDD(rdd -> {

      System.out.println("RDD COUNT = "+ rdd.count());
    });

    streamingContext.start();
    streamingContext.awaitTermination();
  }
}
