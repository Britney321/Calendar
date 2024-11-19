package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a log of alarm system events.
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
public class OccurrenceLog implements Iterable<Occurrence> {
    /**
     * the only EventLog in the system (Singleton Design Pattern)
     */
    private static OccurrenceLog theLog;
    private Collection<Occurrence> occurrences;

    /**
     * Prevent external construction.
     * (Singleton Design Pattern).
     */
    private OccurrenceLog() {
        occurrences = new ArrayList<Occurrence>();
    }

    /**
     * Gets instance of EventLog - creates it
     * if it doesn't already exist.
     * (Singleton Design Pattern)
     *
     * @return instance of EventLog
     */
    public static OccurrenceLog getInstance() {
        if (theLog == null) {
            theLog = new OccurrenceLog();
        }
        return theLog;
    }

    /**
     * Adds an event to the event log.
     *
     * @param e the event to be added
     */
    public void logEvent(Occurrence e) {
        occurrences.add(e);
    }

    /**
     * Clears the event log and logs the event.
     */
    public void clear() {
        occurrences.clear();
        logEvent(new Occurrence("Event log cleared."));
    }

    @Override
    public Iterator<Occurrence> iterator() {
        return occurrences.iterator();
    }
}
