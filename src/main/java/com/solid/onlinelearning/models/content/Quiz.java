package com.solid.onlinelearning.models.content;

public class Quiz implements LearningComponent {
    private final String name;
    private final int durationMinutes;

    public Quiz(String name, int durationMinutes) {
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
        System.out.println(indent + "- Quiz: " + name + " (" + durationMinutes + " min)");
    }
}
