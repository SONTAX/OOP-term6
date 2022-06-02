package model;

import lombok.Data;

@Data
public class Fill {
  private int userId;
  private int drinkId;
  private Integer amount;

  public Fill(int userId, int drinkId, Integer amount) {
    this.userId = userId;
    this.drinkId = drinkId;
    this.amount = amount;
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

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}
