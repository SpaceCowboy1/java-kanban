public class Main {

    public static void main(String[] args) {

        Epic epic = new Epic("EpicTask", "EpicTaskDescr");
        SubTask subTask = new SubTask("subTask", "subTaskDescr", Progress.NEW);
        SubTask subTask1 = new SubTask("subTask1", "anotherDescr", Progress.NEW);
        SubTask subTask2 = new SubTask("StandAloneTask", "someDescr", Progress.NEW);


        TaskManager manager = new TaskManager();
        manager.addTask(epic);
        manager.addTask(subTask);
        manager.addTask(subTask1);
        manager.addTask(subTask2);

        manager.addSubtaskToEpic(subTask, epic);
        manager.addSubtaskToEpic(subTask1, epic);

        /*
        METHODS TO TEST

        addTask (Task task)
        addSubtaskToEpic(SubTask subTask, Epic epic)
        getTasks()
        getEpicTasks()
        getSubTasks()
        updateTask (SubTask oldTask, SubTask newTask)
        updateEpicTask (Epic oldEpic, Epic newEpic)
        removeAllTasks()
        removeTaskById(int id)
        removeEpicTaskById(int id)
        getTaskById(int id)
         */

    }
}
