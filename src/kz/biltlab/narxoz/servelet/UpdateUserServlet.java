package kz.biltlab.narxoz.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.biltlab.narxoz.db.DBConnection;
import kz.biltlab.narxoz.db.Items;
import kz.biltlab.narxoz.db.Users;

import java.io.IOException;

@WebServlet(value = "/updateprofile")
public class UpdateUserServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String email = (request.getParameter("user_email"));
    String name = request.getParameter("user_name");
    String password = request.getParameter("user_password");

    Users user = DBConnection.getUser(email);
    if (user != null) {
      user.setFullName(name);
      user.setPassword(password);
      DBConnection.updateUser(user);
      request.getSession().removeAttribute("currentUser");
      response.sendRedirect("/login?update");
    } else {
      response.sendRedirect("/");
    }

  }

}