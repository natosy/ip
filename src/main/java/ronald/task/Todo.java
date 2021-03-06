package ronald.task;

import ronald.RonaldException;
import ronald.storage.Storage;

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
     * @param command user input.
     * @return Todo task.
     * @throws RonaldException if insufficient or invalid arguments are passed.
     */
    public static Task createTodo(String[] command) throws RonaldException {

        assert command != null : "Todo commands cannot be null";

        if (command.length == 1) {
            throw new RonaldException("There's not enough information about your Todo order!");
        }
        return new Todo(command[1]);
    }

    public String getFormattedStorageString() {
        return "TODO"
                + Storage.SPLITTER
                + (isDone() ? "1" : "0")
                + Storage.SPLITTER
                + getDescription()
                + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
