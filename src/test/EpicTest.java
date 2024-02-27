package test;

import controllers.HistoryManager;
import controllers.InMemoryHistoryManager;
import controllers.InMemoryTaskManager;
import controllers.TaskManager;
import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import status.Progress;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    TaskManager taskManager;
    Epic task;
    SubTask subTask1;
    SubTask subTask2;

    @BeforeEach
    void beforeEach() {
        taskManager = new InMemoryTaskManager();
        task = new Epic("Test addNewTask", "Test addNewTask description");
        taskManager.addEpic(task);
    }

    @Test
    void addEpic() {
        assertNotNull(taskManager.getEpicById(0), "Задача не найдена.");
        assertEquals(task, taskManager.getEpicById(0), "Задачи не совпадают.");

        final List<Epic> tasks = taskManager.getEpics();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void epicStatusSubTasksNew() {
        subTask1 = new SubTask("Subtask1", "descr", Progress.NEW);
        subTask2 = new SubTask("Subtask1", "descr", Progress.NEW);

        taskManager.addSubTaskToEpic(task, subTask1);
        taskManager.addSubTaskToEpic(task, subTask2);
        assertEquals(task.getProgress(), Progress.NEW, "Неверный статус эпика");
    }

  @Test
    void epicStatusSubTaskInProgress() {
        subTask1 = new SubTask("Subtask1", "descr", Progress.IN_PROGRESS);
        subTask2 = new SubTask("Subtask1", "descr", Progress.NEW);

        taskManager.addSubTaskToEpic(task, subTask1);
        taskManager.addSubTaskToEpic(task, subTask2);
        assertEquals(task.getProgress(), Progress.IN_PROGRESS, "Неверный статус эпика");
    }

    @Test
    void epicStatusSubTasksDone() {
        subTask1 = new SubTask("Subtask1", "descr", Progress.DONE);
        subTask2 = new SubTask("Subtask1", "descr", Progress.DONE);

        taskManager.addSubTaskToEpic(task, subTask1);
        taskManager.addSubTaskToEpic(task, subTask2);
        assertEquals(task.getProgress(), Progress.DONE, "Неверный статус эпика");
    }

    @Test
    void epicUpdateSubTask() {
        subTask1 = new SubTask("Subtask1", "descr", Progress.DONE);
        subTask2 = new SubTask("Subtask1", "descr", Progress.NEW);

        taskManager.addSubTaskToEpic(task, subTask1);
        assertEquals(task.getProgress(), Progress.DONE, "Неверный статус эпика");

        taskManager.addSubTaskToEpic(task, subTask2);
        assertEquals(task.getProgress(), Progress.IN_PROGRESS, "Неверный статус эпика");
    }

    @Test
    void addEpicWithSubTasks() {
        subTask1 = new SubTask("Subtask1", "descr", Progress.DONE);
        subTask2 = new SubTask("Subtask1", "descr", Progress.NEW);
        taskManager.addSubTaskToEpic(task, subTask1);
        taskManager.addSubTaskToEpic(task, subTask2);
        assertEquals(2, taskManager.getSubTasks().size(), "Подзадача не записывается в HashMap");
        assertEquals(2, task.getSubTasks().size(), "Подзадача не привязывается к Эпику");
    }

}