package test;

import controllers.TaskManager;
import model.Epic;
import model.SubTask;
import controllers.InMemoryTaskManager;
import model.Task;
import status.Progress;

public class Main {


    public static void main(String[] args) {


        Task task = new Task("1", "ss", Progress.NEW);
        Task task1 = new Task("2", "ss", Progress.NEW);
        Epic epic = new Epic("3", "ss");
       SubTask subTask1 = new SubTask("Subtask1", "descr", Progress.DONE);

        InMemoryTaskManager manager = new InMemoryTaskManager();

        manager.addTask(task);
        manager.addTask(task1);
        manager.addEpic(epic);

        manager.getTaskById(0);
        manager.getTaskById(1);
        manager.getEpicById(2);
        manager.getEpicById(2);
        manager.getEpicById(2);
        manager.getEpicById(2);
        manager.getTaskById(0);
        manager.getTaskById(0);
        manager.addSubTaskToEpic(epic, subTask1);
        printAllTasks(manager);
        manager.removeEpicById(2);
        printAllTasks(manager);


    }
    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);

        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubTasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        System.out.println(manager.getHistory());
    }

}
