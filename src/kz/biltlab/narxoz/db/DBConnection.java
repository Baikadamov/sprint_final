package kz.biltlab.narxoz.db;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
  private static Connection connection;


  static {
    String url = "jdbc:postgresql://localhost:5432/sprinttask2";
    String user = "postgres";
    String password = "12345";
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(url, user, password);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Items> getItems() {
    ArrayList<Items> items = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Items ORDER by id asc"
      );

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Items item = new Items();
        item.setId(resultSet.getLong("id"));
        item.setName(resultSet.getString("name"));
        item.setDescription(resultSet.getString("description"));
        item.setPrice(resultSet.getDouble("price"));

        items.add(item);
      }
      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return items;
  }

  public static void addItem(Items item) {
    try {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO items (name,description,price) " +
          "VALUES (?,?,?)");
      statement.setString(1, item.getName());
      statement.setString(2, item.getDescription());
      statement.setDouble(3, item.getPrice());

      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Items getItem(int id) {
    Items item = null;
    try {
      PreparedStatement statement = connection.prepareStatement("" +
          "SELECT * from items WHERE id = ? LIMIT 1");
      statement.setInt(1, id);

      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        item = new Items();
        item.setId(resultSet.getLong("id"));
        item.setName(resultSet.getString("name"));
        item.setDescription(resultSet.getString("description"));
        item.setPrice(resultSet.getDouble("price"));

      }

      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return item;
  }

  public static void updateItem(Items item) {
    try {
      PreparedStatement statement = connection.prepareStatement("" +
          "UPDATE items " +
          "SET name=?," +
          "description=?," +
          "price=?" +
          "WHERE id = ?");
      statement.setString(1, item.getName());
      statement.setString(2, item.getDescription());
      statement.setDouble(3, item.getPrice());
      statement.setDouble(4, item.getId());
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void deleteItem(int id) {
    try {
      PreparedStatement statement = connection.prepareStatement("" +
          "DELETE FROM items WHERE id = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static Users getUser(String email) {
    Users users = null;
    try {
      PreparedStatement statement = connection.prepareStatement(" " + "SELECT * FROM users WHERE email = ?");
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        users = new Users();
        users.setId(resultSet.getLong("id"));
        users.setEmail(resultSet.getString("email"));
        users.setPassword(resultSet.getString("password"));
        users.setFullName(resultSet.getString("full_Name"));
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }


  public static void updateUser(Users user) {
    try {
      PreparedStatement statement = connection.prepareStatement("" +
          "UPDATE users " +
          "SET full_name=?," +
          "password=?" +
          "WHERE email = ?");
      statement.setString(1, user.getFullName());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getEmail());
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void addUser(Users user) {
    try {
      PreparedStatement statement = connection.prepareStatement("" +
          "INSERT INTO users (email, password, full_name, role_id) " +
          "VALUES (?, ?, ?, ?)");

      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getFullName());
      statement.setInt(4, user.getRole());

      statement.executeUpdate();
      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static ArrayList<News> getNews() {
    ArrayList<News> news = new ArrayList<>();
    try {

      PreparedStatement statement = connection.prepareStatement("" +
          "SELECT n.id, n.title, n.content, n.category_id ,nc.name , n.user_id, u.full_name, n.post_date " +
          "FROM news n " +
          "INNER JOIN users u ON u.id = n.user_id " +
          "INNER JOIN news_categories nc ON nc.id = n.category_id " + // add a space here
          "ORDER BY n.post_date DESC ");


      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {

        News n = new News();
        n.setId(resultSet.getInt("id"));
        n.setTitle(resultSet.getString("title"));
        n.setContent(resultSet.getString("content"));
        n.setPostDate(resultSet.getTimestamp("post_date"));

        Category category = new Category();

        category.setId(resultSet.getInt("category_id"));
        category.setName(resultSet.getString("name"));
        n.setCategory(category);

        Users user = new Users();
        user.setId(resultSet.getLong("user_id"));
        user.setFullName(resultSet.getString("full_name"));
        n.setUser(user);

        news.add(n);
      }

      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return news;
  }


  public static void addNews(News news) {
    try {
      PreparedStatement statement = connection.prepareStatement("" +
          "INSERT INTO news (title, content, post_date, user_id , category_id) " +
          "VALUES (?, ?, NOW(), ? , ?)");

      statement.setString(1, news.getTitle());
      statement.setString(2, news.getContent());

      Users user = news.getUser();
      if (user != null) {
        statement.setLong(3, user.getId());
      } else {
        statement.setNull(3, Types.BIGINT);
      }

      statement.setInt(4, news.getCategory().getId());

      statement.executeUpdate();
      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static News getNewsById(Long id) {
    News news = null;
    try {

      PreparedStatement statement = connection.prepareStatement("" +
          "SELECT n.id, n.title, n.content,n.category_id ,nc.name , n.user_id, u.full_name, n.post_date " +
          "FROM news n " +
          "INNER JOIN users u ON u.id = n.user_id " +
          "INNER JOIN news_categories nc ON nc.id = n.category_id " +
          "WHERE n.id = ? ");

      statement.setLong(1, id);

      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {

        news = new News();
        news.setId(resultSet.getInt("id"));
        news.setTitle(resultSet.getString("title"));
        news.setContent(resultSet.getString("content"));
        news.setPostDate(resultSet.getTimestamp("post_date"));

        Category category = new Category();

        category.setId(resultSet.getInt("category_id"));
        category.setName(resultSet.getString("name"));
        news.setCategory(category);

        Users user = new Users();
        user.setId(resultSet.getLong("user_id"));
        user.setFullName(resultSet.getString("full_name"));
        news.setUser(user);

      }

      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return news;
  }




  public static void addComment(Comment comment) {
    try {

      PreparedStatement statement = connection.prepareStatement("" +
          "INSERT INTO comments (comment, user_id, news_id, post_date) " +
          "VALUES (?, ?, ?, NOW())");

      statement.setString(1, comment.getComment());
      statement.setLong(2, comment.getUser().getId());
      statement.setLong(3, comment.getNews().getId());

      statement.executeUpdate();
      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static ArrayList<Comment> getComments(int newsId) {

    ArrayList<Comment> comments = new ArrayList<>();

    try {

      PreparedStatement statement = connection.prepareStatement("" +
          "SELECT co.id, co.comment, co.post_date, co.news_id, co.user_id, u.full_name " +
          "FROM comments co " +
          "INNER JOIN users u ON u.id = co.user_id " +
          "WHERE co.news_id = ? " +
          "ORDER BY co.post_date DESC");

      statement.setLong(1, newsId);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {

        Comment comment = new Comment();
        comment.setId(resultSet.getLong("id"));
        comment.setComment(resultSet.getString("comment"));
        comment.setPostDate(resultSet.getTimestamp("post_date"));
        Users user = new Users();
        user.setId(resultSet.getLong("user_id"));
        user.setFullName(resultSet.getString("full_name"));
        comment.setUser(user);

        News news = new News();
        news.setId(resultSet.getInt("news_id"));
        comment.setNews(news);

        comments.add(comment);
      }
      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return comments;
  }


  public static ArrayList<Category> getCategories() {
    ArrayList<Category> categories = new ArrayList<>();
    try {

      PreparedStatement statement = connection.prepareStatement("" +
          "SELECT * FROM news_categories ORDER BY name ASC");

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));

        categories.add(category);
      }
      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return categories;
  }


  public static Category getCategory(int id) {
    Category category = null;
    try {

      PreparedStatement statement = connection.prepareStatement("" +
          "SELECT * FROM news_categories WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        category = new Category();
        category.setName(resultSet.getString("name"));
        category.setId(resultSet.getInt("id"));
      }
      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return category;
  }

}
