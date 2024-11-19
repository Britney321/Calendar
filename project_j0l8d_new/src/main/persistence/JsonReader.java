package persistence;

import model.*;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Calendar read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
//        Occurrence occurrence = new Occurrence("Calendar loaded.");
//        OccurrenceLog.getInstance().logEvent(occurrence);
        return parseCalendar(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses calendar from JSON object and returns it
    private Calendar parseCalendar(JSONObject jsonObject) {
        Calendar calendar = new Calendar();
        addEvents(calendar, jsonObject);
        addTasks(calendar, jsonObject);
        return calendar;
    }

    // MODIFIES: c
    // EFFECTS: parses events from JSON object and adds them to calendars
    private void addEvents(Calendar c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("events");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(c, nextEvent);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses thingy from JSON object and adds it to calendar
    private void addEvent(Calendar c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int year = jsonObject.getInt("year");
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        int time = jsonObject.getInt("time");
        int duration = jsonObject.getInt("duration");
        Event event = new Event(name, year, month, day, time, duration);
        c.createNewEvent(event);
    }

    // MODIFIES: c
    // EFFECTS: parses events from JSON object and adds them to calendars
    private void addTasks(Calendar c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(c, nextTask);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses thingy from JSON object and adds it to calendar
    private void addTask(Calendar c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int year = jsonObject.getInt("year");
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        Task task = new Task(name, year, month, day);
        c.createNewTask(task);
    }
}
