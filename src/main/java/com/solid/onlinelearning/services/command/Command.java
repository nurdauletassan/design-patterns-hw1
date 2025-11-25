package com.solid.onlinelearning.services.command;

/**
 * Command interface encapsulating an action with optional undo support.
 */
public interface Command {
    void execute();
    default void undo() {
        // optional
    }
}
