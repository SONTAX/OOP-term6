package model;

import lombok.Data;

@Data
public class Bill {
  private int userId;
  private int drinkId;
  private int amount;
  private int cost;
  private boolean paid;

  public Bill(int userId, int drinkId, int amount, int cost, boolean paid) {
    this.userId = userId;
    this.drinkId = drinkId;
    this.amount = amount;
    this.cost = cost;
    this.paid = paid;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getDrinkId() {
    return drinkId;
  }

  public void setDrinkId(int drinkId) {
    this.drinkId = drinkId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }
}
