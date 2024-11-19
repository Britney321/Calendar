package model;

import org.json.JSONObject;
import persistence.Writable;

// A task with name, and date
public class Task implements Writable {
    private String title;
    private int year;
    private int month;
    private int day;

    //REQUIRES: title has a non-zero length, year >= 1000 and year <= 9999, 1< month <=12,
    //1 <= day <= 31
    //EFFECTS: Construct an event with a title and due date
    public Task(String title, int year, int month, int day) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
    }

/*    //MODIFIES: this
    //EFFECTS: change the title to the new title
    public void changeTitle(String title) {
        this.title = title;
    }

    //MODIFIES: this
    //EFFECTS: change the date to the new date
    public void changeDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }*/

    //EFFECTS: return a string of the date of the task
    public int getDate() {
        String dateStatement;
        String stringMonth = String.valueOf(month);
        String stringDay = String.valueOf(day);
        if (month <= 9) {
            stringMonth = "0" + month;
        }
        if (day <= 9) {
            stringDay = "0" + day;
        }
        dateStatement = year + stringMonth + stringDay;
        return Integer.valueOf(dateStatement);
    }

    // EFFECTS: return the Title of an event
    public String getTitle() {
        return title;
    }

    // EFFECTS: return the Year of an event
    public int getYear() {
        return year;
    }

    // EFFECTS: return the month of an event
    public int getMonth() {
        return month;
    }

    // EFFECTS: return the time of an event
    public int getDay() {
        return day;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", title);
        json.put("year", year);
        json.put("month", month);
        json.put("day", month);
        return json;
    }
}


