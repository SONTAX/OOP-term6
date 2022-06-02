package dao;

import domain.DataBaseRepository;
import model.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkDao {

  private final DataBaseRepository repository;

  public DrinkDao(DataBaseRepository repository) {
    this.repository = repository;
  }

  public List<Drink> getDrinksFromDB() throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM drink"
        );
    List<Drink> getDrinks = runningQuery(sqlQuery);
    return getDrinks;
  }

  public List<Drink> getDrinksFromDB(Integer amount) throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM drink WHERE amount >= %s",
            amount
        );
    List<Drink> getDrinks = runningQuery(sqlQuery);
    return getDrinks;
  }

  public List<Drink> getDrinksFromDB(Integer userId, Boolean paid) throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM " +
                "drink INNER JOIN bill ON drink.id = bill.drink_id " +
                "WHERE bill.user_id = %d AND bill.paid = %s",
            userId, paid
        );
    List<Drink> getDrinks = runningQuery(sqlQuery);
    return getDrinks;
  }

  public Drink getDrinkFromDB(Integer id) throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM drink WHERE id = %s",
            id
        );
    List<Drink> getDrinks = runningQuery(sqlQuery);
    return getDrinks.size() != 1 ? null : getDrinks.get(0);
  }

  public void updateDrink(Integer id, Integer amount) throws SQLException {
    final String sqlQuery =
        String.format(
            "UPDATE drink SET amount = %s WHERE id = %d",
            amount, id
        );
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      statement.execute();
    }
  }

  private List<Drink> runningQuery(String sqlQuery) throws SQLException {
    List<Drink> getDrinks = new ArrayList<>();
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        Drink drink = new Drink(result.getInt("id"), result.getString("name"), result.getInt("amount"), result.getInt("price"));
        getDrinks.add(drink);
      }
    }
    return getDrinks;
  }
}
