package factory;

import dao.BillDao;
import dao.DrinkDao;
import dao.UserDao;
import dao.FillDao;
import domain.DataBaseRepository;
import service.CoffeeService;
import service.LoginService;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {

  private static final Map<Class<?>, Object> beans = new HashMap<>();

  static {
    DataBaseRepository repository = DataBaseRepository.init();
    beans.put(DataBaseRepository.class, repository);
    UserDao userDao = new UserDao(repository);
    beans.put(UserDao.class, userDao);
    LoginService loginService = new LoginService(userDao);
    beans.put(LoginService.class, loginService);
    DrinkDao drinkDao = new DrinkDao(repository);
    beans.put(DrinkDao.class, drinkDao);
    BillDao billDao = new BillDao(repository);
    beans.put(BillDao.class, billDao);
    FillDao fillDao = new FillDao(repository);
    beans.put(FillDao.class, fillDao);
    CoffeeService coffeeService = new CoffeeService(drinkDao, billDao, fillDao);
    beans.put(CoffeeService.class, coffeeService);
  }

  public static Object getBean(Class<?> beanClass) {
    return beans.get(beanClass);
  }
}
