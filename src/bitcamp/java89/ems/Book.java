package bitcamp.java89.ems;

import java.io.Serializable;

public class Book implements Serializable {
  String title;
  String author;
  String press;
  int date;
  int price;
  int page;
  boolean sale;

  public Book() {}

  public Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }
}
