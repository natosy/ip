package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        isDone = false;
    }

    @Override
    public String getDate() {
        return " (by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }

    public String getFormattedString() {
        return "DEADLINE::" + (isDone? "1::" : "0::") + description + "::" + date + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDate() + "\n";
    }
}