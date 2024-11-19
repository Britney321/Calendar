package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {
    Calendar testCalendar;
    EventList testEvents;
    Event testEvent1;
    Event testEvent2;
    TaskList testTasks;
    Task testTask1;
    Task testTask2;

    @BeforeEach
    void runBefore() {
        testCalendar = new Calendar();
        testEvents = new EventList();
        testEvent1 = new Event("A", 2023, 1, 1, 1, 1);
        testEvent2 = new Event("A", 2023, 2, 2, 2, 2);
        testTasks = new TaskList();
        testTask1 = new Task("A", 2023, 1, 1);
        testTask2 = new Task("A", 2023, 2, 2);
    }

    @Test
    void testCreateNewEvent() {
        testCalendar.createNewEvent(testEvent1);
        assertTrue(testCalendar.sortEvent().contains((testEvent1)));
        assertFalse(testCalendar.sortEvent().contains((testEvent2)));
    }

    @Test
    void testCreateNewTask() {
        testCalendar.createNewTask(testTask1);
        assertTrue(testCalendar.listAllTasks().getAllTasks().contains((testTask1)));
        assertFalse(testCalendar.listAllTasks().getAllTasks().contains((testTask2)));
    }

    @Test
    void testRemoveEvent() {
        testCalendar.createNewEvent(testEvent1);
        testCalendar.createNewEvent(testEvent2);
        testCalendar.removeEvent("A");
        assertFalse(testCalendar.sortEvent().contains((testEvent1)));
        assertTrue(testCalendar.sortEvent().contains((testEvent2)));
        testCalendar.removeEvent("C");
        assertEquals(1, testCalendar.sortEvent().size());
    }

    @Test
    void testSortEvent() {
        List<Event> testList;
        testList = new ArrayList<>();
        testList.add(testEvent1);
        testList.add(testEvent2);
        testCalendar.createNewEvent(testEvent2);
        testCalendar.createNewEvent(testEvent1);
        List<Event> sorted = testCalendar.sortEvent();
        assertEquals(testList, sorted);
    }

    @Test
    void testListAllDailyEvents() {
        List<Event> testList;
        testList = new ArrayList<>();
        testList.add(testEvent1);
        testCalendar.createNewEvent(testEvent2);
        testCalendar.createNewEvent(testEvent1);
        List<Event> eventList = testCalendar.listAllDailyEvents(2023,1,1);
        assertEquals(testList, eventList);
    }

    @Test
    void testCompleteTask() {
        testCalendar.createNewTask(testTask1);
        testCalendar.createNewTask(testTask2);
        testCalendar.completeTask("A");
        List<Task> testList = new ArrayList<>();
        testList.add(testTask2);
        assertEquals(testList, testCalendar.listAllTasks().getAllTasks());
        testCalendar.completeTask("C");
        assertEquals(testList, testCalendar.listAllTasks().getAllTasks());
    }

    @Test
    void testListAllTasks() {
        testCalendar.createNewTask(testTask1);
        testCalendar.createNewTask(testTask2);
        List<Task> testList = new ArrayList<>();
        testList.add(testTask1);
        testList.add(testTask2);
        assertEquals(testList, testCalendar.listAllTasks().getAllTasks());
    }
}
