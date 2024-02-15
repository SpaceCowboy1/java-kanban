import model.Epic;
import model.SubTask;
import controllers.TaskManager;
import model.Task;
import status.Progress;

public class Main {

    public static void main(String[] args) {

        Epic epic = new Epic("EpicTask", "EpicTaskDescr");
        SubTask subTask = new SubTask("subTask", "subTaskDescr", Progress.NEW);
        SubTask subTask1 = new SubTask("subTask1", "anotherDescr", Progress.NEW);
        SubTask subTask2 = new SubTask("subtask2", "someDescr", Progress.DONE);
        SubTask subTask3 = new SubTask("subtask3", "someDescr", Progress.IN_PROGRESS);
        Task task = new Task("s", "ss", Progress.NEW);
        Task task1 = new Task("222", "ss", Progress.NEW);

        TaskManager manager = new TaskManager();

        manager.addEpic(epic);
        manager.addSubTaskToEpic(epic, subTask1);
        manager.addSubTaskToEpic(epic, subTask);
        System.out.println(manager.getEpicById(0));
        System.out.println("=======================");

        manager.updateSubTask(subTask, subTask2);
        System.out.println(manager.getEpicById(0));
        System.out.println(manager.getSubTaskOfEpic(0));
        System.out.println("=========================");

        manager.removeSubTaskById(1);
        System.out.println(manager.getEpicById(0));
        System.out.println(manager.getSubTaskOfEpic(0));


    }
}
