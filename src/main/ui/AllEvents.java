package ui;

import model.Calendar;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//New frame that shows the list of all events that have been added to the calendar
public class AllEvents extends JFrame {
    private Calendar calendar;
    private JTextArea label;

    //EFFECTS: constructor for new window when listing all events
    public AllEvents(Calendar calendar) {
        createFrame();
        this.calendar = calendar;
    }

    //MODIFIES: this
    // EFFECTS: creates a new window
    public void createFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("All Events");
                setDefaultCloseOperation(HIDE_ON_CLOSE);
                setPreferredSize(new Dimension(400, 400));
                label = new JTextArea(3,30);
                label.setText(events());
                setLayout(new FlowLayout());
                label.setLineWrap(true);
                add(label);
                pack();
                setLocationRelativeTo(null);
                setVisible(true);
                setResizable(false);
            }
        });
    }

    // EFFECTS: produces a list of all events in a calendar
    public String events() {
        List<Event> events = calendar.sortEvent();
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
}
