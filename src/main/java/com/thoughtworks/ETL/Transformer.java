package com.thoughtworks.ETL;

import org.bson.Document;

import java.util.List;

class Transformer {
  static void transform(List<Document> documents) {
    for (Document document: documents) {
      String firstName = document.getString("firstName");
      String lastName = document.getString("lastName");
      int age = document.getInteger("age");
      Loader.load(firstName, lastName, age);
    }
  }
}
