package com.solid.onlinelearning.models.content;

/**
 * Flyweight interface describing learning content with shared intrinsic state.
 */
public interface LearningComponent {
    void render(String studentId, double progressPercentage);
}
