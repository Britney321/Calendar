package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    Task testTask;

    @BeforeEach
    void runBefore() {
        testTask = new Task("Test", 2023, 10, 3);
    }

    // test constructor
    @Test
    void testConstructor() {
        assertEquals("Test", testTask.getTitle());
        assertEquals(2023, testTask.getYear());
        assertEquals(10, testTask.getMonth());
        assertEquals(3, testTask.getDay());
    }

/*    @Test
    void testChangeTitle() {
        testTask.changeTitle("New Title");
        assertEquals("New Title", testTask.getTitle());
    }

    @Test
    void testChangeDate() {
        testTask.changeDate(2024, 5, 1);
        assertEquals(2024, testTask.getYear());
        assertEquals(5, testTask.getMonth());
        assertEquals(1, testTask.getDay());
    }*/


    @Test
    void testGetDate() {
        assertEquals(20231003, testTask.getDate());
        Task testTask2 = new Task("Test 2", 2020, 1, 3);
        assertEquals(20200103, testTask2.getDate());
        Task testTask3 = new Task("Test 3", 2020, 1, 20);
        assertEquals(20200120, testTask3.getDate());
        Task testTask4 = new Task("Test 4", 2020, 19, 23);
        assertEquals(20201923, testTask4.getDate());
    }
}
