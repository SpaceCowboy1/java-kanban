package model;

import status.Progress;

import java.util.ArrayList;

public class Epic extends Task {

   private ArrayList<SubTask> subTasks = new ArrayList<>();


    public Epic(String nameOfTask, String description) {
        super(nameOfTask, description, Progress.NEW);
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }
}
