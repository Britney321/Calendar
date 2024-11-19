package persistence;

import model.Event;
import model.Calendar;
import model.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Calendar calendar = new Calendar();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCalendar() {
        try {
            Calendar calendar = new Calendar();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCalendar.json");
            writer.open();
            writer.write(calendar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCalendar.json");
            calendar = reader.read();
            assertEquals(0, calendar.sortEvent().size());
            assertEquals(0, calendar.listAllTasks().getAllTasks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCalendar() {
        try {
            Calendar calendar = new Calendar();
            calendar.createNewEvent(new Event("A", 2023, 1, 1, 1,1));
            calendar.createNewEvent(new Event("B", 2023, 2, 2, 2,2));
            calendar.createNewTask(new Task("A", 2023, 1, 1));
            calendar.createNewTask(new Task("B", 2023, 2, 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCalendar.json");
            writer.open();
            writer.write(calendar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCalendar.json");
            calendar = reader.read();
            List<Event> events = calendar.sortEvent();
            assertEquals(2, events.size());
            List<Task> tasks = calendar.listAllTasks().getAllTasks();
            assertEquals(2, tasks.size());
            checkEvent("A", calendar.sortEvent().get(0));
            checkEvent("B", calendar.sortEvent().get(1));
            checkTask("A", calendar.listAllTasks().getAllTasks().get(0));
            checkTask("B", calendar.listAllTasks().getAllTasks().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}