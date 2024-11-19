package persistence;

import model.Event;
import model.Task;

import static junit.framework.TestCase.assertEquals;

// code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkEvent(String name, Event event) {
        assertEquals(name, event.getTitle());
    }

    protected void checkTask(String name, Task task) {
        assertEquals(name, task.getTitle());
    }
}
