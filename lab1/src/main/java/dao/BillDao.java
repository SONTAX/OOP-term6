package dao;

import domain.DataBaseRepository;
import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDao {

  private final DataBaseRepository repository;

  public BillDao(DataBaseRepository repository) {
    this.repository = repository;
  }

  public void insertBill(Integer userId, Integer drinkId, Integer amount, Integer cost) throws SQLException {
    final String sqlQuery =
        String.format(
            "INSERT INTO bill(user_id, drink_id, amount, cost) " +
                "VALUES (%d, %d, %d, %d)",
            userId, drinkId, amount, cost
        );
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      statement.execute();
    }
  }

  public List<Bill> getBillsForClient(Integer userId) throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM bill WHERE user_id = %d AND paid = false",
            userId
        );
    return getBills(sqlQuery);
  }

  public List<Bill> getBillsForAdmin() throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM bill WHERE paid = false"
        );
    return getBills(sqlQuery);
  }

  private List<Bill> getBills(String sqlQuery) throws SQLException {
    final var bills = new ArrayList<Bill>();
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        Bill bill = new Bill(result.getInt("user_id"), result.getInt("drink_id"), result.getInt("amount"), result.getInt("cost"), result.getBoolean("paid"));
        bills.add(bill);
      }
    }
    return bills;
  }

  public boolean existBill(Integer userId, Integer drinkId) throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM bill WHERE user_id = %d AND drink_id = %d AND paid = false",
            userId, drinkId
        );
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      ResultSet result = statement.executeQuery();
      return result.next();
    }
  }

  public boolean existActiveBill(Integer userId, Integer drinkId) throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM bill WHERE user_id = %d AND drink_id = %d AND paid = true",
            userId, drinkId
        );
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      ResultSet result = statement.executeQuery();
      return result.next();
    }
  }

  public void deleteBill(Integer userId, Integer drinkId) throws SQLException {
    final String sqlQuery =
        String.format(
            "DELETE FROM bill WHERE user_id = %d AND drink_id = %d AND paid = true",
            userId, drinkId
        );
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      statement.execute();
    }
  }

  public void updateBill(Integer userId, Integer drinkId) throws SQLException {
    final String sqlQuery =
        String.format(
            "UPDATE bill SET paid = true WHERE user_id = %d AND drink_id = %d AND paid = false",
            userId, drinkId
        );
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      statement.execute();
    }
  }
}
