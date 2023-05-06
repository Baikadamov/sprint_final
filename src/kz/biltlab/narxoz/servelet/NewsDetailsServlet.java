package kz.biltlab.narxoz.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.biltlab.narxoz.db.Comment;
import kz.biltlab.narxoz.db.DBConnection;
import kz.biltlab.narxoz.db.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Long id = Long.parseLong(request.getParameter("id"));
    News news = DBConnection.getNewsById(id);
    request.setAttribute("news", news);

    if (news != null) {
      ArrayList<Comment> comments = DBConnection.getComments(news.getId());
      request.setAttribute("comments", comments);
    }
    request.getRequestDispatcher("/newsdetails.jsp").forward(request, response);
  }
}