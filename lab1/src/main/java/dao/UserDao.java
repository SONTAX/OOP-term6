package dao;

import domain.AppricationProperties;
import domain.DataBaseRepository;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

  private final DataBaseRepository repository;

  public UserDao(DataBaseRepository repository) {
    this.repository = repository;
  }

  public List<User> getUserFromDB(String login, String password, int isAdmin) throws SQLException {
    List<User> getUsers = new ArrayList<>();
    final String sqlQuery =
        String.format(
            "SELECT * FROM user_ WHERE login %s AND password %s %s",
            "= '" + login + "'",
            "= '" + password + "'",
            isAdmin == AppricationProperties.SELECT_ALL_INT ? "" : "AND isAdmin = " + isAdmin
        );
    try (Connection connection = repository.createConnection();
         PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        User user = new User(result.getInt("id"), result.getString("name"), result.getString("surname"),
            result.getString("login"), result.getString("password"), result.getInt("isAdmin"));
        getUsers.add(user);
      }
    }
    return getUsers;
  }
}
