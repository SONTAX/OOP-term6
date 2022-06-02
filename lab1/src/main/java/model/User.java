package model;

import lombok.Data;

@Data
public class User {
  private int id;
  private String name;
  private String surname;
  private String login;
  private String password;
  private int isAdmin;

  public User(int id, String name, String surname, String login, String password, int isAdmin) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.login = login;
    this.password = password;
    this.isAdmin = isAdmin;
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

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAdmin() {
    return isAdmin;
  }

  public void setAdmin(int admin) {
    isAdmin = admin;
  }
}
