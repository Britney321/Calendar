package model;

import java.util.ArrayList;
import java.util.List;

// a list of the tasks that have been added
public class TaskList {
    private ArrayList<Task> tasks;
    private ArrayList<Task> completedTasks;
    private ArrayList<Task> incompleteTasks;

    //EFFECTS: construct an empty list of tasks and empty list of completed tasks
    public TaskList() {
        tasks = new ArrayList<>();
        incompleteTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds task to list of tasks
    public void addTask(Task task) {
        tasks.add(task);
        incompleteTasks.add(task);
    }

    //MODIFIES: this
    //EFFECTS: removes task from list of tasks
    public void removeTask(Task task) {
        tasks.remove(task);
        incompleteTasks.remove(task);
        completedTasks.remove(task);
    }

    //MODIFIES: this
    //EFFECTS: removes  task to list of tasks
    public void completeTask(Task task) {
        completedTasks.add(task);
        incompleteTasks.remove(task);
    }

    //EFFECTS: returns a list of tasks that are due on a given date
    public ArrayList<Task> listDayTasks(int year, int month, int day) {
        ArrayList<Task> dayTasks = new ArrayList<>();
        for (Task t : incompleteTasks) {
            if (t.getYear() == year && t.getMonth() == month && t.getDay() == day) {
                dayTasks.add(t);
            }
        }
        return dayTasks;
    }

    //EFFECTS: return a list of all tasks
    public List<Task> getAllTasks() {
        return tasks;
    }

    //EFFECTS: return a list of completed tasks
    public List<Task> getCompleteTasks() {
        return completedTasks;
    }

    //EFFECTS: return a list of incomplete tasks
    public List<Task> getIncompleteTasks() {
        return incompleteTasks;
    }

    //EFFECTS: returns the size of the tasklist
    public int getTaskListSize() {
        return tasks.size();
    }

    //EFFECTS: return the task at the specified index
    public Task getTaskIndex(int index) {
        return tasks.get(index);
    }
}
