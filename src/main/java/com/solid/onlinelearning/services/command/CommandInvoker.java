package com.solid.onlinelearning.services.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Invoker keeps history to support undo/redo.
 */
public class CommandInvoker {
    private final Deque<Command> history = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    public void execute(Command command) {
        command.execute();
        history.push(command);
        redoStack.clear();
    }

    public void undoLast() {
        if (history.isEmpty()) {
            return;
        }
        Command last = history.pop();
        last.undo();
        redoStack.push(last);
    }

    public void redoLast() {
        if (redoStack.isEmpty()) {
            return;
        }
        Command cmd = redoStack.pop();
        cmd.execute();
        history.push(cmd);
    }
}
