package com.solid.onlinelearning.models.content;

import java.util.ArrayList;
import java.util.List;

public class Module implements LearningComponent {
    private final String name;
    private final List<LearningComponent> children = new ArrayList<>();

    public Module(String name) {
        this.name = name;
    }

    public void add(LearningComponent component) {
        children.add(component);
    }

    public void remove(LearningComponent component) {
        children.remove(component);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDurationMinutes() {
        return children.stream()
                .mapToInt(LearningComponent::getDurationMinutes)
                .sum();
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "Module: " + name + " [" + getDurationMinutes() + " min]");
        String childIndent = indent + "  ";
        for (LearningComponent child : children) {
            child.print(childIndent);
        }
    }
}
