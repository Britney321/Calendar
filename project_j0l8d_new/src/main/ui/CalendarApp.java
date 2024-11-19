package ui;


import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

// Terminal interface for calendar
public class CalendarApp {
    private Calendar calendar;
    private static final String JSON_STORE = "./data/calendar.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECT: constructs a calendar with empty and empty list of tasks
    //and empty list of events
    public CalendarApp() {
        this.calendar = new Calendar();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: determines the item to be modified (events or tasks) or to stop the program
    public void action() {
        Scanner eventAdded = new Scanner(System.in); // Create a Scanner object
        System.out.println("Modify Events? [E], Modify Tasks? [T], Load Calendar [L], or Quit [Q]");

        String actionRequired = eventAdded.nextLine();
        if (Objects.equals(actionRequired, "E")) {
            eventAction();
        } else if (Objects.equals(actionRequired, "T")) {
            taskAction();
        } else if (Objects.equals(actionRequired, "L")) {
            loadCalendar();
        } else if (Objects.equals(actionRequired, "Q")) {
            quitAction();
        } else if (! Objects.equals(actionRequired, "Q")) {
            action();
        }
    }

    //MODIFIES: this
    //EFFECTS: determines how events should be modified
    public void eventAction() {
        Scanner action = new Scanner(System.in); // Create a Scanner object
        System.out.println("Add [A], Remove [R], List all events [L], List day events [D], or Quit [Q]?");

        String actionRequired = action.nextLine();
        if (Objects.equals(actionRequired, "A")) {
            createNewEvent();
            eventAction();
        } else if (Objects.equals(actionRequired, "R")) {
            removeEvent();
            eventAction();
        } else if (Objects.equals(actionRequired, "L")) {
            listAllEvents();
            eventAction();
        } else if (Objects.equals(actionRequired, "D")) {
            listAllDailyEvents();
            eventAction();
        } else if (Objects.equals(actionRequired, "Q")) {
            action();
        } else {
            eventAction();
        }
    }

    //MODIFIES: this
    //EFFECTS: determines how events should be modified
    public void taskAction() {
        Scanner action = new Scanner(System.in); // Create a Scanner object
        System.out.println("Add[A], Complete [C], List all tasks [L], or Quit [Q]?");

        String actionRequired = action.nextLine();
        if (Objects.equals(actionRequired, "A")) {
            createNewTask();
            taskAction();
        } else if (Objects.equals(actionRequired, "C")) {
            completeTask();
            taskAction();
        } else if (Objects.equals(actionRequired, "L")) {
            listAllTasks();
            taskAction();
        } else if (Objects.equals(actionRequired, "Q")) {
            action();
        } else {
            taskAction();
        }
    }

    // EFFECTS: determines if the user wants to save progress or not
    public void quitAction() {
        Scanner action = new Scanner(System.in); // Create a Scanner object
        System.out.println("Would you like to save changes? [Y]es or [N]o?");

        String actionRequired = action.nextLine();
        if (Objects.equals(actionRequired, "Y")) {
            saveCalendar();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a new event to the list of events
    public void createNewEvent() {
        Scanner event = new Scanner(System.in);
        System.out.println("Enter Event Name, Year, Month, Day, Time, Duration");


        String title = event.nextLine();
        int year = event.nextInt();
        int month = event.nextInt();
        int day = event.nextInt();
        int time = event.nextInt();
        int duration = event.nextInt();


        Event event1 = new Event(title, year, month, day, time, duration);
        if (calendar.createNewEvent(event1)) {
            System.out.println("Name: " + title);
            System.out.println("Date: " + year + "/" + month + "/" + day);
            System.out.println("Time: " + time);
            System.out.println("Duration: " + duration);
        } else {
            System.out.println("Event cannot be added");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a new tasks to the list of tasks
    public void createNewTask() {
        Scanner task = new Scanner(System.in);
        System.out.println("Enter Task Name, Year, Month, & Day");


        String title = task.nextLine();
        int year = task.nextInt();
        int month = task.nextInt();
        int day = task.nextInt();

        Task task1 = new Task(title, year, month, day);
        calendar.createNewTask(task1);

        System.out.println("Name: " + title);  // Output user input
        System.out.println("Date: " + year + "/" + month + "/" + day);
    }

    //MODIFIES: this
    //EFFECTS: removes a task of given name from the list of tasks
    public void removeEvent() {
        Scanner event = new Scanner(System.in);
        System.out.println("Name of Event");
        String eventName = event.nextLine();

        calendar.removeEvent(eventName);
    }

    //MODIFIES: this
    // EFFECTS: returns a list of all events in chronological order
    public void listAllEvents() {
        List<Event> eventList = calendar.sortEvent();
        for (Event e : eventList) {
            System.out.println(e.getTitle() + " " + e.getDate());
        }
    }

    //EFFECTS: lists all the events that are occurring on a given day
    public void listAllDailyEvents() {
        Scanner date = new Scanner(System.in);
        System.out.println("Enter Year, Month, Day");
        int year = date.nextInt();
        int month = date.nextInt();
        int day = date.nextInt();

        List<Event> dayEvents = calendar.listAllDailyEvents(year, month, day);
        for (Event e: dayEvents) {
            System.out.println(e.getTitle() + " " + e.getTime());
        }
    }

    //EFFECTS: removes a tasks once it has been completed
    public void completeTask() {
        Scanner task = new Scanner(System.in);
        System.out.println("Name of Task");
        String taskName = task.nextLine();

        calendar.completeTask(taskName);
    }

    // EFFECTS: produces a list of all the tasks that have not been completed
    public void listAllTasks() {
        TaskList taskList = calendar.listAllTasks();
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            System.out.println(taskList.getTaskIndex(i).getTitle() + " " + taskList.getTaskIndex(i).getDate());
        }
    }


    // EFFECTS: saves the calendar to file
    public void saveCalendar() {
        try {
            jsonWriter.open();
            jsonWriter.write(calendar);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads calendar from file
    public void loadCalendar() {
        try {
            calendar = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
            action();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
