package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToDoList {
    private static int currentId = 1;
    private static HashMap<Integer, Task> toDoList = new HashMap<Integer, Task>();

    public static List<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList.addAll(toDoList.values());
        return taskList;
    }

    public static Task getTask(int id) {
        return toDoList.get(id);
    }

    public static int setTask(String description) {
        int id = currentId++;
        toDoList.put(id, new Task(id,description));
        return id;
    }

    public static void deleteTask(int id) {
        toDoList.remove(id);
    }
}
