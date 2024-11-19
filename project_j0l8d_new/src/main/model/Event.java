package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent an even having a Title, date, time, and duration
public class Event implements Writable {
    private String title;
    private int month;
    private int day;
    private int year;
    private int time;
    private int duration;

    /*
     * REQUIRES: title has a non-zero length, year >= 1000 and year <= 9999, 1< month <=12,
     * 1 <= day <= 31, 0 <= time < 24
     * EFFECTS: Construct an event with a title, date, time and duration
     */
    public Event(String title, int year, int month, int day, int time, int duration) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.duration = duration;
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
    }

    //MODIFIES: this
    //EFFECTS: change the time to the new time
    public void changeTime(int time) {
        this.time = time;
    }

    //MODIFIES: this
    //EFFECTS: change the duration to the new duration
    public void changeDuration(int duration) {
        this.duration = duration;
    }*/

    //EFFECTS: return a string of the date of the event
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

    // EFFECTS: return the title of an event
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

    // EFFECTS: return the day of an event
    public int getDay() {
        return day;
    }

    // EFFECTS: return the time of an event
    public int getTime() {
        return time;
    }

    // EFFECTS: return the duration of an event
    public int getDuration() {
        return duration;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", title);
        json.put("year", year);
        json.put("month", month);
        json.put("day", day);
        json.put("time", time);
        json.put("duration", duration);
        return json;
    }
}
