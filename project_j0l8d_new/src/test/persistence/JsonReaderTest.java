package persistence;


import model.Calendar;
import model.Event;
import model.Task;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

//// code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Calendar calendar = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCalendar() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCalendar.json");
        try {
            Calendar calendar = reader.read();
            assertEquals(0, calendar.listAllTasks().getTaskListSize());
            assertEquals(0, calendar.sortEvent().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCalendar() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCalendar.json");
        try {
            Calendar calendar = reader.read();
            List<Event> events = calendar.sortEvent();
            List<Task> tasks = calendar.listAllTasks().getAllTasks();
            assertEquals(2, events.size());
            assertEquals(2, tasks.size());
            checkEvent("A", calendar.sortEvent().get(0));
            checkEvent("B", calendar.sortEvent().get(1));
            checkTask("A", calendar.listAllTasks().getAllTasks().get(0));
            checkTask("B", calendar.listAllTasks().getAllTasks().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}