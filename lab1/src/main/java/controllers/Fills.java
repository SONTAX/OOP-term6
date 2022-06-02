package controllers;

import domain.AppricationProperties;
import factory.BeanFactory;
import model.Fill;
import service.CoffeeService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/fills")
public class Fills extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("sessionId") == null || session.getAttribute("sessionId") == AppricationProperties.ERROR) {
      response.getWriter().println("Unauthorized");
      return;
    }
    CoffeeService service = (CoffeeService) BeanFactory.getBean(CoffeeService.class);
    try {
      List<Fill> fills = service.fills(session);
      response.getWriter().println(fills);
    } catch (Exception e) {
      response.getWriter().println(e.getMessage());
    }
  }
}
