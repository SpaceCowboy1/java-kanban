import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    HashMap<Integer, Epic> epicTasks = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();


    public void addTask (Task task) {
        switch (task.getType()) {
            case "class Epic" :
                epicTasks.put(task.getId(), (Epic) task);
                return;
            case "class SubTask" :
                subTasks.put(task.getId(), (SubTask) task);
                return;
            default :
                break;

        }
    }

    public void addSubtaskToEpic(SubTask subTask, Epic epic) {
        epic.getSubTasks().add(subTask);
        subTask.setEpicId(epic.getId());
        int progressCounter = 0;

        for (SubTask obj : epic.getSubTasks()) {
            if (obj.getProgress() == Progress.IN_PROGRESS) {
                epic.setProgress(Progress.IN_PROGRESS);
            } else if (obj.getProgress() == Progress.DONE) {
                progressCounter++;
            }
        }
        if (progressCounter == epic.getSubTasks().size()) {
            epic.setProgress(Progress.DONE);
        }
    }


    ArrayList<SubTask> getTasks() {
        ArrayList<SubTask> temp = new ArrayList<>();
        for (SubTask obj : subTasks.values()) {
            if (obj.getEpicId() == -1) {
                temp.add(obj);
            }
        }
        return temp;
    }

    ArrayList<Epic> getEpicTasks() {
        return new ArrayList<>(epicTasks.values());
    }

    ArrayList<SubTask> getSubTasks() {
        ArrayList<SubTask> temp = new ArrayList<>();
        for (Epic obj : epicTasks.values()) {
            temp.addAll(obj.getSubTasks());
        }
        return temp;
    }

    public void updateTask (SubTask oldTask, SubTask newTask) {
        subTasks.put(oldTask.getId(), newTask);
    }

    public void updateEpicTask (Epic oldEpic, Epic newEpic) {
        epicTasks.put(oldEpic.getId(), newEpic);
    }

   public void removeAllTasks() {
        subTasks.clear();
        epicTasks.clear();
   }

    void removeTaskById(int id) {
        subTasks.remove(id);
    }

    void removeEpicTaskById(int id) {
        epicTasks.remove(id);
    }

    public Task getTaskById(int id) {
        HashMap<Integer, Task> tasks = new HashMap<>();
        tasks.putAll(subTasks);
        tasks.putAll(epicTasks);
        return tasks.get(id);
    }


}
