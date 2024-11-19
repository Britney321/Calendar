package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event testEvent;

    @BeforeEach
    void runBefore() {
        testEvent = new Event("Test", 2023, 10, 3, 12, 2);
    }

    // test constructor
    @Test
    void testConstructor() {
        assertEquals("Test", testEvent.getTitle());
        assertEquals(2023, testEvent.getYear());
        assertEquals(10, testEvent.getMonth());
        assertEquals(3, testEvent.getDay());
        assertEquals(12, testEvent.getTime());
        assertEquals(2, testEvent.getDuration());

    }

/*    @Test
    void testChangeTitle() {
        testEvent.changeTitle("New Title");
        assertEquals("New Title", testEvent.getTitle());
    }

    @Test
    void testChangeDate() {
        testEvent.changeDate(2024, 5, 1);
        assertEquals(2024, testEvent.getYear());
        assertEquals(5, testEvent.getMonth());
        assertEquals(1, testEvent.getDay());
    }

    @Test
    void testChangeTime() {
        testEvent.changeTime(15);
        assertEquals(15, testEvent.getTime());
    }

    @Test
    void testChangeDuration() {
        testEvent.changeDuration(4);
        assertEquals(4, testEvent.getDuration());
    }
        */


    @Test
    void testGetDate() {
        assertEquals(20231003, testEvent.getDate());
        Event testEvent2 = new Event("Test 2", 2020, 1, 3, 4, 1);
        assertEquals(20200103, testEvent2.getDate());
        Event testEvent3 = new Event("Test 3", 2020, 1, 10, 2, 1);
        assertEquals(20200110, testEvent3.getDate());
        Event testEvent4 = new Event("Test 4", 2020, 9, 9, 2, 1);
        assertEquals(20200909, testEvent4.getDate());
    }
}