package service;

import dao.BillDao;
import dao.DrinkDao;
import dao.FillDao;
import domain.AppricationProperties;
import lombok.Data;
import model.Bill;
import model.Drink;
import model.Fill;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Data
public class CoffeeService {

  private DrinkDao drinkDao;
  private BillDao billDao;
  private FillDao fillDao;

  public CoffeeService(DrinkDao drinkDao, BillDao billDao, FillDao fillDao) {
    this.drinkDao = drinkDao;
    this.billDao = billDao;
    this.fillDao = fillDao;
  }

  public List<Drink> getDrinks(HttpSession session) throws SQLException {
    if (session.getAttribute("sessionId") == AppricationProperties.ID_ADMIN)
      return drinkDao.getDrinksFromDB();
    return drinkDao.getDrinksFromDB(1);
  }

  public List<Drink> getDrinksByUser(HttpSession session, Boolean paid) throws Exception {
    Integer userId = (Integer) session.getAttribute("id");

    return drinkDao.getDrinksFromDB(userId, paid);
  }

  public void buyDrink(HttpSession session, Integer drinkId, Integer amount) throws Exception {
    Drink drink = drinkDao.getDrinkFromDB(drinkId);

    if (drink == null)
      throw new Exception("Drink not found");

    if (drink.getAmount() == 0)
      throw new Exception("Drink runs out");

    Integer cost = amount * drink.getPrice();
    Integer userId = (Integer) session.getAttribute("id");

    billDao.insertBill(userId, drinkId, amount, cost);
  }

  public List<Bill> bills(HttpSession session) throws Exception {
    if (session.getAttribute("sessionId") == AppricationProperties.ID_ADMIN)
      return billDao.getBillsForAdmin();
    else return billDao.getBillsForClient((Integer) session.getAttribute("id"));
  }

  public void fill(HttpSession session, Integer userId, Integer drinkId, Integer amount) throws Exception {
    if (session.getAttribute("sessionId") == AppricationProperties.ID_USER)
      throw new Exception("Has not permissions");

    fillDao.insertFill(userId, drinkId, amount);

    drinkDao.updateDrink(drinkId, amount + drinkDao.getDrinkFromDB(drinkId).getAmount());
  }


  public List<Fill> fills(HttpSession session) throws Exception {
    if (session.getAttribute("sessionId") == AppricationProperties.ID_ADMIN)
      return fillDao.getFills();
    else return fillDao.getFillsByUser((Integer) session.getAttribute("id"));
  }

  public void pay(HttpSession session, Integer userId, Integer drinkId) throws Exception {
    if (session.getAttribute("sessionId") == AppricationProperties.ID_USER)
      throw new Exception("Has not permissions");

    if (!billDao.existBill(userId, drinkId))
      throw new Exception("Bill not found or already payed");
    billDao.updateBill(userId, drinkId);
  }
}
