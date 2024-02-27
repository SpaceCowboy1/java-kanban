package controllers;


import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubTaskToEpic(Epic epic, SubTask subTask);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<SubTask> getSubTasks();

    void clearTasks();

    void clearEpics();

    void clearSubtasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    SubTask getSubTaskById(int id);

    void updateTask(Task currentTask, Task newTask);

    void updateEpicTask(Epic currentEpic, Epic newEpic);

    void updateSubTask(SubTask currentSubTask, SubTask newSubTask);

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubTaskById(int id);

    ArrayList<SubTask> getSubTaskOfEpic(int epicId);

    List<Task> getHistory();


}
