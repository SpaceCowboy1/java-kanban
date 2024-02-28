package controllers;



import model.Epic;
import model.SubTask;
import model.Task;
import status.Progress;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private HashMap<Integer, Epic> epicTasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HistoryManager historyManager = Managers.getDefaultHistory();

    private int id = 0;

    @Override
    public void addTask(Task task) {
        tasks.put(id++, task);
    }

    @Override
    public void addEpic(Epic epic) {
        epicTasks.put(id++, epic);
    }

    @Override
    public void addSubTaskToEpic(Epic epic, SubTask subTask) {
        int key;
        for (Map.Entry<Integer, Epic> entry : epicTasks.entrySet()) {
            if (entry.getValue().equals(epic)) {
                key = entry.getKey();
                subTask.setEpicId(key);
                epic.getSubTasks().add(subTask);
                subTasks.put(id++, subTask);
                statusCheck(key);
                return;
            }
        }
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicTasks.values());
    }

    @Override
    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Override
    public void clearEpics() {
        epicTasks.clear();
        subTasks.clear();
    }

    @Override
    public void clearSubtasks() {
        for (Epic epic : epicTasks.values()) {
            epic.getSubTasks().clear();
            epic.setProgress(Progress.NEW);
        }
        subTasks.clear();
    }

    @Override
    public Task getTaskById(int id) {
        if (tasks.containsKey(id)) {
            historyManager.add(tasks.get(id));
            return tasks.get(id);
        }
        return null;
    }

    @Override
    public Epic getEpicById(int id) {
        if (epicTasks.containsKey(id)) {
            historyManager.add(epicTasks.get(id));
            return epicTasks.get(id);
        }
        return null;
    }

    @Override
    public SubTask getSubTaskById(int id) {
        if (subTasks.containsKey(id)) {
            historyManager.add(subTasks.get(id));
            return subTasks.get(id);
        }
        return null;
    }

    @Override
    public void updateTask(Task currentTask, Task newTask) {
        int key;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (entry.getValue().equals(currentTask)) {
                key = entry.getKey();
                tasks.put(key, newTask);
            }
        }
    }

    @Override
    public void updateEpicTask(Epic currentEpic, Epic newEpic) {
        int key;
        for (Map.Entry<Integer, Epic> entry : epicTasks.entrySet()) {
            if (entry.getValue().equals(currentEpic)) {
                key = entry.getKey();
                newEpic.getSubTasks().addAll(currentEpic.getSubTasks());
                epicTasks.put(key, newEpic);
                statusCheck(key);
                return;
            }
        }
    }

    @Override
    public void updateSubTask(SubTask currentSubTask, SubTask newSubTask) {
        int key;
        int epicId = currentSubTask.getEpicId();
        for (Map.Entry<Integer, SubTask> entry : subTasks.entrySet()) {
            if (entry.getValue().equals(currentSubTask)) {
                key = entry.getKey();
                epicTasks.get(epicId).getSubTasks().remove(currentSubTask);
                epicTasks.get(epicId).getSubTasks().add(newSubTask);
                subTasks.put(key, newSubTask);
                statusCheck(epicId);
                return;
            }
        }
    }

    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public void removeEpicById(int id) {
        for (SubTask task : subTasks.values()) {
            if (task.getEpicId() == id) {
              subTasks.entrySet().removeIf(entry -> (task.equals(entry.getValue())));

            }
        }
        epicTasks.get(id).getSubTasks().clear();
        epicTasks.remove(id);

    }

    @Override
    public void removeSubTaskById(int id) {
        int epicId = subTasks.get(id).getEpicId();
        epicTasks.get(epicId).getSubTasks().remove(subTasks.get(id));
        subTasks.remove(id);
        statusCheck(epicId);
    }

    private void statusCheck(int epicId) {
        ArrayList<SubTask> temp = new ArrayList<>(epicTasks.get(epicId).getSubTasks());
        if (temp.isEmpty()) {
            epicTasks.get(epicId).setProgress(Progress.NEW);
            return;
        }
        ArrayList<Progress> progresses = new ArrayList<>();
         for (SubTask task : temp) {
             progresses.add(task.getProgress());
         }

        if (Collections.frequency(progresses, progresses.get(0)) == progresses.size()) {
            epicTasks.get(epicId).setProgress(progresses.get(0));
        } else {
            epicTasks.get(epicId).setProgress(Progress.IN_PROGRESS);
        }

    }

    @Override
    public ArrayList<SubTask> getSubTaskOfEpic(int epicId) {
        return new ArrayList<>(epicTasks.get(epicId).getSubTasks());
    }

    public List getHistory() {
        return historyManager.getHistory();
    }

}
