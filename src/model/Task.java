package model;

import java.util.Objects;
import status.Progress;

public class Task {
    private String nameOfTask;
    private String description;
    private Progress progress;



    public Task(String nameOfTask, String description, Progress progress) {
        this.nameOfTask = nameOfTask;
        this.description = description;
        this.progress = progress;

    }


    public Progress getProgress() {
        return progress;
    }


    public void setProgress(Progress progress) {
        this.progress = progress;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Task otherTask = (Task) obj;
        return Objects.equals(nameOfTask, otherTask.nameOfTask) && Objects.equals(description, otherTask.description);
    }

    @Override
    public String toString() {
        return nameOfTask + " " + description + " " + progress;
    }


}

