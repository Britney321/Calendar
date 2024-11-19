package ui;

import model.Calendar;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Frame that shows a list of all events that occur on a given day
public class FilteredEvents extends JFrame  {
    private Calendar calendar;
    private JLabel label;
    private JLabel eventLabel;
    private int year;
    private int month;
    private int day;

    // EFFECTS: constructor for new window of filtered events
    public FilteredEvents(Calendar calendar, int year, int month, int day) {
        createFrame();
        this.calendar = calendar;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    //EFFECTS: produces a list of events that occur on a given date
    public String filterEvent() {
        List<Event> events = calendar.listAllDailyEvents(year, month, day);
        if (events.size() == 0) {
            return "";
        } else {
            String eventString = events.get(0).getTitle();
            events.remove(0);
            for (Event e : events) {
                eventString = eventString + ", " + e.getTitle();
            }
            return eventString;
        }
    }

    //EFFECTS: creates a new window that lists the events that occur on a given day
    public void createFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("filtered events");
                setDefaultCloseOperation(HIDE_ON_CLOSE);
                setPreferredSize(new Dimension(400, 400));
                label = new JLabel(year + "/" + month + "/" + day);
                eventLabel = new JLabel(filterEvent());
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                setLayout(new FlowLayout());
                add(label);
                add(eventLabel);
                pack();
                setLocationRelativeTo(null);
                setVisible(true);
                setResizable(false);
            }
        });
    }

}
