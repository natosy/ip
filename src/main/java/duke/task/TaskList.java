package duke.task;

import duke.command.CommandType;
import duke.DukeException;
import duke.Duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.Ui.*;

public class TaskList {
    public static void addTask(CommandType type, String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("I need to know more about your " + command[0]
                    + " before I can add it to your order list!");
        }
        switch (type) {
        case TODO:
            addTodo(command[1]);
            break;
        case DEADLINE:
            addDeadline(command[1]);
            break;
        case EVENT:
            addEvent(command[1]);
            break;
        }
    }

    public static void addTodo(String desc) {
        Task task = new Todo(desc);
        Duke.tasks.add(task);
        displayAddedTask(task);
    }

    public static void addDeadline(String desc) throws DukeException {
        String[] args = desc.split(" /by ", 2);
        if (args.length == 1 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new DukeException("Looks like your order isn't complete...");
        }
        Task task = new Deadline(args[0], convertStringToDate(args[1]));
        Duke.tasks.add(task);
        displayAddedTask(task);
    }

    public static void addEvent(String desc) throws DukeException {
        String[] args = desc.split(" /at ", 2);
        if (args.length == 1 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new DukeException("Looks like your order isn't complete...");
        }
        Task task = new Event(args[0], convertStringToDate(args[1]));
        Duke.tasks.add(task);
        displayAddedTask(task);
    }

    public static void markDone(String[] command) throws DukeException {
        if (command.length > 2) {
            throw new DukeException("I can't serve more than 1 order at a time!");
        }
        try {
            Task toMarkDone = Duke.tasks.get(Integer.parseInt(command[1]) - 1);
            toMarkDone.markDone();
            displayDone(toMarkDone);
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }

    public static void deleteTask(String[] command) throws DukeException {
        try {
            Task task = Duke.tasks.remove(Integer.parseInt(command[1]) - 1);
            displayRemovedTask(task);
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
    public static LocalDateTime convertStringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            throw new DukeException("There was something wrong with the format of your date and/or time.\n" +
                    "Make sure it's in the format <dd/MM/yyyy HHmm>!");
        }
    }
}