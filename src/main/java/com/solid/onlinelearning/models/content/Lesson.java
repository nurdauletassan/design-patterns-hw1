package com.solid.onlinelearning.models.content;

public class Lesson implements LearningComponent {
    private final String name;
    private final int durationMinutes;

    public Lesson(String name, int durationMinutes) {
        this.name = name;
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "- Lesson: " + name + " (" + durationMinutes + " min)");
    }
}
