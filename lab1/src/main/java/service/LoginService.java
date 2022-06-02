package service;

import dao.UserDao;
import domain.AppricationProperties;
import lombok.Data;
import model.User;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Data
public class LoginService {

  private UserDao userDao;

  public LoginService(UserDao userDao) {
    this.userDao = userDao;
  }

  public void loginAccount(HttpSession session, String pass, String login) throws SQLException {
    int selectAllInt = AppricationProperties.SELECT_ALL_INT;
    List<User> users = userDao.getUserFromDB(login, pass, selectAllInt);
    System.out.println(users);
    if (users.size() == 1) {
      User user = users.get(0);
      session.setAttribute("name", user.getName());
      session.setAttribute("surname", user.getSurname());
      session.setAttribute("id", user.getId());
      if (user.getIsAdmin() == 0) {
        session.setAttribute("sessionId", AppricationProperties.ID_USER);
        session.setAttribute("type", "User");
      } else {
        session.setAttribute("sessionId", AppricationProperties.ID_ADMIN);
        session.setAttribute("type", "Admin");
      }
    } else {
      session.setAttribute("sessionId", AppricationProperties.ERROR);
      session.setAttribute("type", "Wrong");
    }
  }
}
