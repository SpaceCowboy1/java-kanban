package model;

import status.Progress;

public class SubTask extends Task {
    private int epicId = -1;

    public SubTask(String nameOfTask, String description, Progress progress) {
        super(nameOfTask, description, progress);
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

}
