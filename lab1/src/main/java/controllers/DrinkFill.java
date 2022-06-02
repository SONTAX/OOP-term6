package controllers;

import domain.AppricationProperties;
import factory.BeanFactory;
import service.CoffeeService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/drinks/fill")
public class DrinkFill extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("sessionId") == null || session.getAttribute("sessionId") == AppricationProperties.ERROR) {
      response.getWriter().println("Unauthorized");
      return;
    }

    if (request.getParameter("userId") == null) {
      response.getWriter().println("Need userId query param");
      return;
    }
    if (request.getParameter("drinkId") == null) {
      response.getWriter().println("Need drinkId query param");
      return;
    }
    if (request.getParameter("amount") == null) {
      response.getWriter().println("Need amount query param");
      return;
    }
    CoffeeService service = (CoffeeService) BeanFactory.getBean(CoffeeService.class);
    try {
      service.fill(session, Integer.parseInt(request.getParameter("userId")),Integer.parseInt(request.getParameter("drinkId")), Integer.parseInt(request.getParameter("amount")));
    } catch (Exception e) {
      response.getWriter().println(e.getMessage());
      return;
    }
    response.getWriter().println("Done");
  }
}
