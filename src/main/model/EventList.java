package model;

import java.util.ArrayList;
import java.util.List;


// Represents a list of the events that have been added
public class EventList {
    private final ArrayList<Event> events;

    //EFFECTS: construct an empty list of event s
    public EventList() {
        events = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: takes an event and adds it to the list of events if there is availability at the date and time
    public boolean addEvent(Event event) {
        if (isAvailable(event.getYear(), event.getMonth(), event.getDay(), event.getTime(), event.getDuration())) {
            events.add(event);
            return true;
        } else {
            return false;
        }
    }


    //MODIFIES: this
    //EFFECTS: takes an event in the list and removes it from the event list
    public void removeEvent(Event event) {
        events.remove(event);
    }

    //EFFECTS: takes a dates a produces a list of the events taking place on that date
    // in the order they have been added
    public ArrayList<Event> listDayEvents(int year, int month, int day) {
        ArrayList<Event> dayEvents = new ArrayList<>();
        for (Event e : this.events) {
            if (e.getYear() == year && e.getMonth() == month && e.getDay() == day) {
                dayEvents.add(e);
            }
        }
        return dayEvents;
    }



    //EFFECTS: checks if an event with given date time and duration can fit into the schedule
    // based on other events in the list
    private boolean isAvailable(int year, int month, int day, int time, int duration) {
        ArrayList<Event> dayEvents;
        dayEvents = listDayEvents(year, month, day);
        int i = time;
        boolean availability = true;
        while (i < time + duration && availability) {
            for (Event e : dayEvents) {
                if (i >= e.getTime() && i < e.getTime() + e.getDuration()) {
                    availability = false;
                    break;
                }
            }
            i++;
        }
        return availability;
    }

    //EFFECTS: return a list of all events that have been added
    public List<Event> getAllEvents() {
        return events;
    }

    //EFFECTS:  return a list of events in the order from earliest to latest
    public ArrayList<Event> sortEvents() {
        ArrayList<Event> sortedList = new ArrayList<>();
        for (Event e : events) {
            if (sortedList.isEmpty() || e.getDate() > sortedList.get(sortedList.size() - 1).getDate()) {
                sortedList.add(e);
            } else {
                int i = 0;
                while (i < sortedList.size()) {
                    if (e.getDate() < sortedList.get(0).getDate()) {
                        sortedList.add(0, e);
                        break;
                    } else if (e.getDate() == sortedList.get(i).getDate()) {
                        sortedList.add(compareTime(e, sortedList.get(i), i), e);
                        break;
                    } else if (e.getDate() > sortedList.get(i).getDate()
                            && e.getDate() < sortedList.get(i + 1).getDate()) {
                        sortedList.add(i + 1, e);
                        break;
                    }
                    i++;
                }
            }
        }
        return sortedList;
    }

    // REQUIRES: event1 and event2 occur on the same date
    // EFFECTS: return the index if an event is earlier and index + 1 if the event is later
    private int compareTime(Event event1, Event event2, int index) {
        if (event1.getTime() > event2.getTime()) {
            return index + 1;
        } else {
            return index;
        }
    }



    //EFFECTS: returns the size of a list
    public int getListSize() {
        return events.size();
    }

    //EFFECTS: return the task at the specified index
    public Event getEventIndex(int index) {
        return events.get(index);
    }
}