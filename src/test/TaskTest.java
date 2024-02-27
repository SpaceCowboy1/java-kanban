package test;

import controllers.HistoryManager;
import controllers.InMemoryHistoryManager;
import controllers.InMemoryTaskManager;
import controllers.TaskManager;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import status.Progress;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    TaskManager taskManager;
    HistoryManager historyManager;
    Task task;

    @BeforeEach
    void beforeEach() {
        taskManager = new InMemoryTaskManager();
        historyManager = new InMemoryHistoryManager();
        task = new Task("Test addNewTask", "Test addNewTask description", Progress.NEW);
        taskManager.addTask(task);
    }

    @Test
    void addNewTask() {
        assertNotNull(taskManager.getTaskById(0), "Задача не найдена.");
        assertEquals(task, taskManager.getTaskById(0), "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void addHistory() {
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void removeTaskTest() {
        taskManager.removeTaskById(0);
        assertTrue(taskManager.getTasks().isEmpty());
    }

}