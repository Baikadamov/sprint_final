package kz.biltlab.narxoz.db;

public class Tasks {
  public Long id;
  public String name;
  public String description;
  public String deadlineDate;

  public Tasks(){

  }

  public Tasks(Long id, String name, String description, String deadlineDate) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.deadlineDate = deadlineDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDeadlineDate() {
    return deadlineDate;
  }

  public void setDeadlineDate(String deadlineDate) {
    this.deadlineDate = deadlineDate;
  }
}
