package kz.biltlab.narxoz.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kz.biltlab.narxoz.db.Category;
import kz.biltlab.narxoz.db.DBConnection;
import kz.biltlab.narxoz.db.News;
import kz.biltlab.narxoz.db.Users;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/add-news-page")
public class addNewsServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Users user = (Users) request.getSession().getAttribute("currentUser");

    if (user != null ) {

      String title = request.getParameter("title");
      String content = request.getParameter("content");
      int category_id = Integer.parseInt(request.getParameter("category"));


      Category category = DBConnection.getCategory(category_id);
      if (category != null) {

        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCategory(category);
        news.setUser(user);
        DBConnection.addNews(news);
      }
      response.sendRedirect("/");
    } else {
      response.sendRedirect("/login");
    }

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Users user = (Users) request.getSession().getAttribute("currentUser");
    if (user != null) {
      ArrayList<Category> categories = DBConnection.getCategories();
      request.setAttribute("category", categories);
      request.getRequestDispatcher("/addnews.jsp").forward(request, response);

    } else {
      response.sendRedirect("/login");
    }
  }
}