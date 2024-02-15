package controllers;


import model.Epic;
import model.SubTask;
import model.Task;
import status.Progress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    private HashMap<Integer, Epic> epicTasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private int id = 0;

    public void addTask (Task task) {
        tasks.put(id++, task);
    }

    public void addEpic (Epic epic) {
        epicTasks.put(id++, epic);
    }

    public void addSubTaskToEpic (Epic epic, SubTask subTask) {
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

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicTasks.values());
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public void clearTasks() {
        tasks.clear();
    }

    public void clearEpics() {
        for (Epic epic : epicTasks.values()) {
            epic.getSubTasks().clear();
        }
        epicTasks.clear();
        subTasks.clear();
    }

    public void clearSubtasks() {
        for (Epic epic : epicTasks.values()) {
            epic.getSubTasks().clear();
        }
        subTasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epicTasks.get(id);
    }

    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public void updateTask(Task currentTask, Task newTask) {
        int key;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (entry.getValue().equals(currentTask)) {
                key = entry.getKey();
                tasks.put(key, newTask);
            }
        }
    }

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

    public void updateSubTask (SubTask currentSubTask, SubTask newSubTask) {
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

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeEpicById(int id) {
        for (SubTask task : subTasks.values()) {
            if (task.getEpicId() == id) {
                subTasks.entrySet().removeIf(entry -> (task.equals(entry.getValue())));
            }
        }
        epicTasks.get(id).getSubTasks().clear();
        epicTasks.remove(id);

    }

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

    public ArrayList<SubTask> getSubTaskOfEpic(int epicId) {
        return new ArrayList<>(epicTasks.get(epicId).getSubTasks());
    }
}
