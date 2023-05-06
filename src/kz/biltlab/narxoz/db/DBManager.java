package kz.biltlab.narxoz.db;

import java.util.ArrayList;

public class DBManager {

  private static final ArrayList<Tasks> tasks = new ArrayList<>();

  private static long id = 4;

  static {
    tasks.add(new Tasks((long)1, "Something", "Something", "2023-04-12"));
    tasks.add(new Tasks((long)2, "Something1", "Something1", "2023-01-12"));
    tasks.add(new Tasks((long)3, "Something2", "Something2", "2023-04-13"));
  }

  public static ArrayList<Tasks> getTasks() {
    return tasks;
  }

  public static void addTasks(Tasks task) {
    task.setId(id);
    tasks.add(task);
    id++;
  }

  public static Tasks getTask(int id) {
    return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
  }


  public static void updateTask(Tasks task) {
    for (int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i).getId() == task.getId()) {
        tasks.set(i, task);
        break;
      }
    }
  }

  public static void deleteTask(int id) {
    for (int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i).getId() == id) {
        tasks.remove(i);
        break;
      }
    }
  }


}
