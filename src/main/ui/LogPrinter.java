package ui;

import model.Occurrence;
import model.OccurrenceLog;

//class for printing OccurrenceLog to the terminal
public class LogPrinter {
    private OccurrenceLog occurrenceLog;

    //EFFECTS: constructs a LogPrinter with given occurance log
    public LogPrinter(OccurrenceLog occurrenceLog) {
        this.occurrenceLog = occurrenceLog;
    }

    //EFFECTS: prints the action and time of each occurrence
    public void printLogAction() {
        for (Occurrence occurrence : occurrenceLog) {
            System.out.println(occurrence.toString());
            System.out.println("");
        }
    }
}

