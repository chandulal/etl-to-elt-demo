package com.thoughtworks.ETL;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Extractor {
  public static void main(String[] args) {
    MongoClient mongo = null;
    try {
      mongo = new MongoClient("localhost", 12345);
      MongoDatabase embededDb = mongo.getDatabase("embeded_db");
      List<Document> documents = extract(embededDb);
      Transformer.transform(documents);
    } finally {
      if (mongo != null) {
        mongo.close();
      }
    }
  }

  private static List<Document> extract(MongoDatabase embededDb) {
    MongoCollection table = embededDb.getCollection("user");
    FindIterable users = table.find();
    List<Document> documents = new ArrayList<>();
    for (Object document : users) {
      documents.add((Document) document);
    }
    return documents;
  }
}
