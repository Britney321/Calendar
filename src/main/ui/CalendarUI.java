package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//Graphical user interface for calendar
public class CalendarUI extends JFrame implements ActionListener {
    private JLabel label;
    private JLabel loadLabel;
    private JLabel eventLabel;
    private JTextField nameField;
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextField timeField;
    private JTextField durationField;
    private Calendar calendar;
    private static final String JSON_STORE = "./data/calendar.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructor for calendarGUI
    public CalendarUI() {
        super("Calendar");
        constructors();
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        label = new JLabel("");
        loadCalendarFeatures();
        addEventFeatures();
        add(new JLabel("                                                                     "));
        add(label);
        add(new JLabel("                                                                    "));
        image();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                LogPrinter logPrinter = new LogPrinter(getOccurrences());
                logPrinter.printLogAction();
                System.exit(0);
            }
        });
    }

    public void constructors() {
        calendar = new Calendar();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
    }

    // MODIFIES: this
    // EFFECTS: specifies features of load calendar
    public void loadCalendarFeatures() {
        loadLabel = new JLabel("       Would you like to load an existing calendar?       ");
        JButton btn1 = new JButton("Load calendar");
        btn1.setActionCommand("load");
        btn1.addActionListener(this);
        JButton saveButton = new JButton("Save calendar");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);
        add(loadLabel);
        add(new JLabel("         "));
        add(btn1);
        add(saveButton);
        add(new JLabel("          "));
    }

    // MODIFIES: this
    // EFFECTS: specifies event features
    public void addEventFeatures() {
        eventTextConstructor();
        eventLabel = new JLabel("               Enter event information below                 ");
        add(eventLabel);
        add(nameField);
        add(yearField);
        add(monthField);
        add(dayField);
        add(timeField);
        add(durationField);
        eventButtons();
    }

    //EFFECTS: specifications for event buttons
    public void eventButtons() {
        JButton addButton = new JButton("Add event");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        JButton removeButton = new JButton("Remove event");
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(this);
        JButton filterButton = new JButton("Filter events");
        filterButton.setActionCommand("filter");
        filterButton.addActionListener(this);
        JButton allButton = new JButton("Show all events");
        allButton.setActionCommand("all");
        allButton.addActionListener(this);
        add(addButton);
        add(removeButton);
        add(allButton);
        add(filterButton);
    }

    // EFFECTS: specifies details of visual component
    public void image() {
        ImageIcon imageIcon = new ImageIcon("./data/CalendarImage.png");
        Image image = imageIcon.getImage(); // transform it
        Image newImage = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel imageInsert = new JLabel(imageIcon);
        add(imageInsert);

    }

    // MODIFIES: this
    // EFFECTS: specifies event text field
    public void eventTextConstructor() {
        nameField = new JTextField("Event name");
        yearField = new JTextField("YYYY");
        monthField = new JTextField("MM");
        dayField = new JTextField("DD");
        timeField = new JTextField("time");
        durationField = new JTextField("duration");
        nameField.setPreferredSize(new Dimension(125, 25));
        yearField.setPreferredSize(new Dimension(50, 25));
        monthField.setPreferredSize(new Dimension(40, 25));
        dayField.setPreferredSize(new Dimension(40, 25));
        timeField.setPreferredSize(new Dimension(40, 25));
        durationField.setPreferredSize(new Dimension(40, 25));
    }

    //This is the method that is called when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("load")) {
            loadCalendar();
        }
        if (e.getActionCommand().equals("save")) {
            saveCalendar();
        }
        eventButtonAction(e);
    }

    //EFFECTS: specifications for results of events with event buttons are pressed
    public void eventButtonAction(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            if (createNewEvent(nameField.getText(), Integer.valueOf(yearField.getText()),
                    Integer.valueOf(monthField.getText()), Integer.valueOf(dayField.getText()),
                    Integer.valueOf(timeField.getText()), Integer.valueOf(durationField.getText()))) {
                label.setText(nameField.getText() + " added");
            } else {
                label.setText(nameField.getText() + " could not be added");
            }
        }
        if (e.getActionCommand().equals("remove")) {
            if (removeEvent()) {
                label.setText(nameField.getText() + " removed");
            } else {
                label.setText(nameField.getText() + " could not be removed");
            }
        }
        if (e.getActionCommand().equals("filter")) {
            listAllDailyEvents(calendar);
        }
        if (e.getActionCommand().equals("all")) {
            listAllEvents(calendar);
        }
    }


    // EFFECTS: saves the calendar to file
    public void saveCalendar() {
        try {
            jsonWriter.open();
            jsonWriter.write(calendar);
            jsonWriter.close();
            label.setText("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            label.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads calendar from file
    public void loadCalendar() {
        try {
            calendar = jsonReader.read();
            label.setText("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            label.setText("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a new event to the list of events
    public boolean createNewEvent(String name, int year, int month, int day, int time, int duration) {
        return calendar.createNewEvent(new Event(name, year, month, day, time, duration));
    }


    //MODIFIES: this
    //EFFECTS: removes a task of given name from the list of tasks
    public boolean removeEvent() {
        return calendar.removeEvent(nameField.getText());
    }

    //MODIFIES: this
    // EFFECTS: returns a list of all events in chronological order
    public void listAllEvents(Calendar calendar) {
        new AllEvents(calendar);
    }

    //EFFECTS: lists all the events that are occurring on a given day
    public void listAllDailyEvents(Calendar calendar) {
        new FilteredEvents(calendar, Integer.valueOf(yearField.getText()),
                        Integer.valueOf(monthField.getText()), Integer.valueOf(dayField.getText()));
    }

    public void exit() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public OccurrenceLog getOccurrences() {
        return calendar.getOccurrences();
    }

}
