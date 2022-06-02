package model;

import lombok.Data;

@Data
public class Drink {
  private int id;
  private String name;
  private Integer amount;
  private Integer price;

  public Drink(int id, String name, Integer amount, Integer price) {
    this.id = id;
    this.name = name;
    this.amount = amount;
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}
