package com.solid.onlinelearning.services.command;

import com.solid.onlinelearning.models.Student;
import com.solid.onlinelearning.services.LearningPlatformFacade;
import com.solid.onlinelearning.services.LoggerSingleton;

/**
 * Command to enroll a student through the facade, enabling queuing/undo semantics.
 */
public class EnrollStudentCommand implements Command {
    private final LearningPlatformFacade facade;
    private final Student student;
    private final String courseId;
    private final String discountType;
    private final String processorType;
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public EnrollStudentCommand(LearningPlatformFacade facade,
                                Student student,
                                String courseId,
                                String discountType,
                                String processorType) {
        this.facade = facade;
        this.student = student;
        this.courseId = courseId;
        this.discountType = discountType;
        this.processorType = processorType;
    }

    @Override
    public void execute() {
        facade.enrollStudent(student, courseId, discountType, processorType);
    }

    @Override
    public void undo() {
        logger.log("[Command] Undo requested: enrollment rollback for " + student.getName()
                + " on course " + courseId + " (logical only)");
    }
}
