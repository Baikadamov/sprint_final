package kz.biltlab.narxoz.db;

import java.sql.Timestamp;

public class News {
  public int id;
  private String title;
  private String content;
  private Users user;
  private Category category;
  private Timestamp postDate;

  public News() {
  }

  public News(int id, String title, String content, Users user, Category category, Timestamp postDate) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.user = user;
    this.category = category;
    this.postDate = postDate;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Timestamp getPostDate() {
    return postDate;
  }

  public void setPostDate(Timestamp postDate) {
    this.postDate = postDate;
  }
}
