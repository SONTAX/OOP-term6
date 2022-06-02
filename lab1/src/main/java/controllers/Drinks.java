package controllers;

import domain.AppricationProperties;
import factory.BeanFactory;
import lombok.SneakyThrows;
import model.Drink;
import service.CoffeeService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/drinks")
public class Drinks extends HttpServlet {
  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("sessionId") == null || session.getAttribute("sessionId") == AppricationProperties.ERROR){
      response.getWriter().println("Unauthorized");
      return;
    }
    CoffeeService service = (CoffeeService) BeanFactory.getBean(CoffeeService.class);
    try {
      List<Drink> drinks = service.getDrinks(session);
      response.getWriter().println(drinks);
    } catch (Exception e) {
      response.getWriter().println(e.getMessage());
    }
  }
}
