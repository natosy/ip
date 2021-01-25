package duke.task;

import duke.DukeException;
import duke.storage.Storage;

/**
 * Class containing data and methods specific to a Todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns Todo task created from arguments representing the user input.
     *
     * @param command user input
     * @return Todo task
     * @throws DukeException if insufficient or invalid arguments are passed
     */
    public static Task createTodo(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("There's not enough information about your Todo order!");
        }
        return new Todo(command[1]);
    }

    public String getFormattedString() {
        return "TODO" + Storage.splitter + (isDone ? "1" : "0") + Storage.splitter + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + getDate() + "\n";
    }
}
