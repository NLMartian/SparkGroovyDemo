package me.nlmartian.java.sparkdemo.model;

/**
 * Created by nlmartian on 4/4/15.
 */
public class Greet {
  private String name;

  private String content;

  public Greet(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return new StringBuilder("Hello ").append(name).append("!").toString();
  }

  public void setContent(String content) {
    this.content = content;
  }
}
