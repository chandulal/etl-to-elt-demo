package com.thoughtworks.ETL;

import org.bson.Document;

import java.util.List;

public class Transformer {
  public static void transform(List<Document> documents) {
    for (Document document: documents) {
      String firstName = document.getString("firstName");
      String lastName = document.getString("lastName");
      int age = document.getInteger("age");
      System.out.println(firstName + lastName + age);
    }
  }
}
