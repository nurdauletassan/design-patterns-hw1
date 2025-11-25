package com.solid.onlinelearning.services.command;

import com.solid.onlinelearning.models.Course;
import com.solid.onlinelearning.services.LoggerSingleton;

/**
 * Command to change course price with undo support.
 */
public class UpdateCoursePriceCommand implements Command {
    private final Course course;
    private final double newPrice;
    private Double previousPrice;
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public UpdateCoursePriceCommand(Course course, double newPrice) {
        this.course = course;
        this.newPrice = newPrice;
    }

    @Override
    public void execute() {
        previousPrice = course.getPrice();
        course.setPrice(newPrice);
        logger.log("[Command] Updated price for " + course.getTitle() + " to $" + newPrice);
    }

    @Override
    public void undo() {
        if (previousPrice != null) {
            course.setPrice(previousPrice);
            logger.log("[Command] Reverted price for " + course.getTitle() + " to $" + previousPrice);
        }
    }
}
