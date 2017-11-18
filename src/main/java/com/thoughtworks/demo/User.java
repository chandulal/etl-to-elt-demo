package com.thoughtworks.demo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
class User {
  @Id
  private ObjectId _id;
  private String firstName;
  private String lastName;
  private int age;

  public ObjectId getId() {
    return _id;
  }

  public void setId(ObjectId _id) {
    this._id = _id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}