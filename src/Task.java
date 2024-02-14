import java.util.Objects;

public abstract class Task {
    private String nameOfTask;
    private String description;
    private int id;
    private static int counter;
    private final String type;
    Progress progress;



    public Task(String nameOfTask, String description, Progress progress) {
        this.nameOfTask = nameOfTask;
        this.description = description;
        this.progress = progress;
        this.id = counter++;
        this.type = String.valueOf(getClass());

    }


    public Progress getProgress() {
        return progress;
    }

    public String getType() {
        return type;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public int getId() {
        return id;
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

