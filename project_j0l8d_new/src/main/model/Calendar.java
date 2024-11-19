package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;


// a calendar with events and tasks
public class Calendar implements Writable {
    EventList events;
    TaskList tasks;

    // EFFECT: constructs a calendar with empty and empty list of tasks
    //and empty list of events
    public  Calendar() {
        this.events = new EventList();
        this.tasks = new TaskList();
    }

    //MODIFIES: this
    //EFFECTS: adds a new event to the list of events
    public boolean createNewEvent(Event event) {
        Occurrence occurrence = new Occurrence(event.getTitle() + " has been added.");
        OccurrenceLog.getInstance().logEvent(occurrence);
        return events.addEvent(event);
    }

    //MODIFIES: this
    //EFFECTS: adds a new tasks to the list of tasks
    public void createNewTask(Task task) {
        tasks.addTask(task);
    }

    //MODIFIES: this
    //EFFECTS: removes a task of given name from the list of tasks
    public boolean removeEvent(String eventName) {
        for (int i = 0; i < events.getListSize(); i++) {
            if (events.getEventIndex(i).getTitle().equals(eventName)) {
                events.removeEvent(events.getEventIndex(i));
                Occurrence occurrence = new Occurrence(eventName + " has been removed.");
                OccurrenceLog.getInstance().logEvent(occurrence);
                return true;
            }
        }
        Occurrence occurrence = new Occurrence(eventName + " could not be removed.");
        OccurrenceLog.getInstance().logEvent(occurrence);
        return false;
    }

    //MODIFIES: this
    // EFFECTS: returns a list of all events in chronological order
    public List<Event> sortEvent() {
        List<Event> eventList = events.sortEvents();
        Occurrence occurrence = new Occurrence("All events were displayed.");
        OccurrenceLog.getInstance().logEvent(occurrence);
        return eventList;
    }

    //EFFECTS: lists all the events that are occurring on a given day
    public List<Event> listAllDailyEvents(int year, int month, int day) {
        events.sortEvents();
        List<Event> dayEvents;
        dayEvents = events.listDayEvents(year, month, day);
        Occurrence occurrence =
                new Occurrence("Events on " + year + "/" + month + "/" + day + " were displayed.");
        OccurrenceLog.getInstance().logEvent(occurrence);
        return dayEvents;
    }

    //EFFECTS: removes a tasks once it has been completed
    public void completeTask(String taskName) {
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            if (tasks.getTaskIndex(i).getTitle().equals(taskName)) {
                tasks.removeTask(tasks.getTaskIndex(i));
            }
        }
    }

    // EFFECTS: produces a list of all the tasks that have not been completed
    public TaskList listAllTasks() {
        return tasks;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("events", eventsToJson());
        json.put("tasks",tasksToJson());
        return json;
    }

    // EFFECTS: returns events in this calendar as a JSON array
    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Event e : events.sortEvents()) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns tasks in this calendar as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks.getAllTasks()) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    public OccurrenceLog getOccurrences() {
        return OccurrenceLog.getInstance();
    }
}