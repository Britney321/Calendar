package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList testTasks;
    private TaskList testTasksEmpty;
    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;
    private Task task5;


    @BeforeEach
    void runBefore() {
        testTasks = new TaskList();
        testTasksEmpty = new TaskList();
        task1 = new Task("A", 2020, 1, 1);
        task2 = new Task("B", 2021, 2, 3);
        task3 = new Task("C", 2022, 5, 6);
        task4 = new Task("D", 2022, 5, 6);
        task5 = new Task("E", 2022, 5, 6);
        testTasks.addTask(task1);
        testTasks.addTask(task2);
        testTasks.addTask(task3);

    }

    @Test
    void testConstructor() {
        assertTrue(testTasksEmpty.getAllTasks().isEmpty());
        assertTrue(testTasksEmpty.getCompleteTasks().isEmpty());
        assertTrue(testTasksEmpty.getIncompleteTasks().isEmpty());
    }

    @Test
    void addTaskTest() {
        testTasksEmpty.addTask(task1);
        testTasksEmpty.addTask(task2);
        testTasksEmpty.addTask(task3);
        List<Task> testList = new ArrayList<>();
        testList.add(task1);
        testList.add(task2);
        testList.add(task3);
        assertEquals(testList, testTasksEmpty.getAllTasks());
        assertEquals(testList, testTasksEmpty.getIncompleteTasks());
        assertTrue(testTasksEmpty.getCompleteTasks().isEmpty());
    }

    @Test
    void testRemoveTask() {
        testTasks.removeTask(task1);
        List<Task> testList = new ArrayList<>();
        testList.add(task2);
        testList.add(task3);
        assertEquals(testList, testTasks.getAllTasks());
        assertEquals(testList, testTasks.getIncompleteTasks());
        assertTrue(testTasksEmpty.getCompleteTasks().isEmpty());
    }

    @Test
    void testCompleteTask() {
        testTasks.completeTask(task1);
        List<Task> testListAll = new ArrayList<>();
        List<Task> testListComplete = new ArrayList<>();
        List<Task> testListIncomplete = new ArrayList<>();
        testListAll.add(task1);
        testListAll.add(task2);
        testListAll.add(task3);
        testListComplete.add(task1);
        testListIncomplete.add(task2);
        testListIncomplete.add(task3);
        assertEquals(testListAll, testTasks.getAllTasks());
        assertEquals(testListIncomplete, testTasks.getIncompleteTasks());
        assertEquals(testListComplete, testTasks.getCompleteTasks());
    }

    @Test
    void testListDayTasks() {
        testTasks.addTask(task4);
        testTasks.addTask(task5);
        List<Task> testList = new ArrayList<>();
        testList.add(task3);
        testList.add(task4);
        testList.add(task5);
        assertEquals(testList, testTasks.listDayTasks(2022, 5, 6));
        testList.clear();
        assertEquals(testList, testTasks.listDayTasks(2023, 5, 6));
        assertEquals(testList, testTasks.listDayTasks(2022, 4, 6));
        assertEquals(testList, testTasks.listDayTasks(2022, 6, 7));
        testList.add(task1);
        assertEquals(testList, testTasks.listDayTasks(2020, 1, 1));
    }

    @Test
    void testGetTaskIndex() {
        assertEquals(task2, testTasks.getTaskIndex(1));
    }

    @Test
    void testGetTaskListSize() {
        assertEquals(3, testTasks.getTaskListSize());
    }
}
