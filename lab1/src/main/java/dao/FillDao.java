package dao;

import domain.DataBaseRepository;
import model.Fill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FillDao {

  private final DataBaseRepository repository;

  public FillDao(DataBaseRepository repository) {
    this.repository = repository;
  }

  public void insertFill(Integer userId, Integer drinkId, Integer amount) throws SQLException {
    final String sqlQuery =
        String.format(
            "INSERT INTO fill(user_id, drink_id, amount) " +
                "VALUES (%d, %d, %d)",
            userId, drinkId, amount
        );
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      statement.execute();
    }
  }

  public List<Fill> getFillsByUser(Integer userId) throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM fill WHERE user_id = %d",
            userId
        );
    return getReports(sqlQuery);
  }

  public List<Fill> getFills() throws SQLException {
    final String sqlQuery =
        String.format(
            "SELECT * FROM fill"
        );
    return getReports(sqlQuery);
  }

  private List<Fill> getReports(String sqlQuery) throws SQLException {
    final var reports = new ArrayList<Fill>();
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        Fill Fill = new Fill(result.getInt("user_id"), result.getInt("drink_id"), result.getInt("amount"));
        reports.add(Fill);
      }
    }
    return reports;
  }
}
