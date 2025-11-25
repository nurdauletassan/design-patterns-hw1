package com.solid.onlinelearning.models.content;

/**
 * Component interface for course content tree.
 */
public interface LearningComponent {
    String getName();
    int getDurationMinutes();
    void print(String indent);
}
