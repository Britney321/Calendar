package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventListTest {

    private EventList testEvents;
    private EventList testEventsEmpty;
    private Event event1;
    private Event event2;
    private Event event3;
    private Event event4;
    private Event event5;
    private Event event6;

    @BeforeEach
    void runBefore() {
        testEvents = new EventList();
        testEventsEmpty = new EventList();
        event1 = new Event("A", 2020, 1, 1, 1, 1);
        event2 = new Event("B", 2021, 2, 3, 4, 2);
        event3 = new Event("C", 2022, 5, 6, 7, 3);
        event4 = new Event("D", 2022, 5, 6, 8, 3);
        event5 = new Event("E", 2022, 5, 6, 10, 3);
        event6 = new Event("F", 2025, 5, 6, 10, 3);
        testEvents.addEvent(event1);
        testEvents.addEvent(event2);
        testEvents.addEvent(event3);
    }

    @Test
    void testConstructor() {
        assertTrue(testEventsEmpty.getAllEvents().isEmpty());
    }

    @Test
    void testAddEventNoConflictBoolean() {
        assertTrue(testEvents.addEvent(event5));
        assertTrue(testEvents.addEvent(event6));
    }

    @Test
    void testAddEventNoConflictList() {
        List<Event> testList;
        testList = new ArrayList<>();
        testList.add(event1);
        testList.add(event2);
        testList.add(event3);
        testList.add(event5);
        testEvents.addEvent(event5);
        assertEquals(testList, testEvents.getAllEvents());
        testList.add(event6);
        testEvents.addEvent(event6);
        assertEquals(testList, testEvents.getAllEvents());
    }

    @Test
    void testAddEventConflictBoolean() {
        assertFalse(testEvents.addEvent(event4));
        assertTrue(testEvents.addEvent(event6));
    }

    @Test
    void testAddEventConflictList() {
        List<Event> testList;
        testList = new ArrayList<>();
        testList.add(event1);
        testList.add(event2);
        testList.add(event3);
        testEvents.addEvent(event4);
        assertEquals(testList, testEvents.getAllEvents());
        testEvents.addEvent(event3);
        assertEquals(testList, testEvents.getAllEvents());
    }

    @Test
    void testRemoveEvent(){
        List<Event> testList;
        testList = new ArrayList<>();
        testList.add(event1);
        testList.add(event2);
        testEvents.removeEvent(event3);
        assertEquals(testList, testEvents.getAllEvents());
    }

    @Test
    void testListDayEvents() {
        testEvents.addEvent(event5);
        List<Event> testList;
        testList = new ArrayList<>();
        testList.add(event3);
        testList.add(event5);
        assertEquals(testList, testEvents.listDayEvents(2022, 5, 6));
        assertEquals(new ArrayList<>(), testEvents.listDayEvents(2022, 5, 7));
        assertEquals(new ArrayList<>(), testEvents.listDayEvents(2022, 9, 6));
        assertEquals(new ArrayList<>(), testEvents.listDayEvents(2027, 5, 6));
    }

    //@Test
    //void testIsAvailable() {
    //    assertTrue(testEvents.isAvailable(2022, 7 ,4, 3, 2));
    //    assertFalse(testEvents.isAvailable(2022, 5 ,6, 8, 2));
    //    assertFalse(testEvents.isAvailable(2022, 5 ,6, 6, 2));
    //}

    @Test
    void testSortEvents() {
        List<Event> testList;
        testList = new ArrayList<>();
        testList.add(event1);
        testList.add(event2);
        testList.add(event3);
        testList.add(event5);
        testList.add(event6);
        testEvents.addEvent(event6);
        testEvents.addEvent(event5);
        List<Event> sorted = testEvents.sortEvents();
        assertEquals(testList, sorted);
        testEventsEmpty.addEvent(event5);
        testEventsEmpty.addEvent(event1);
        testEventsEmpty.addEvent(event3);
        testEventsEmpty.addEvent(event2);
        testEventsEmpty.addEvent(event6);
        List<Event> sorted2 = testEventsEmpty.sortEvents();
        assertEquals(testList, sorted2);
    }
    
    @Test
    void testGetListSize() { 
        assertEquals(3, testEvents.getListSize());
    }
    
    @Test
    void testGetEventIndex() {
        assertEquals(event1, testEvents.getEventIndex(0));
    }
}

