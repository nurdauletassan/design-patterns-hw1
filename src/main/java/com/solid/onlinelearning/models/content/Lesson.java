package com.solid.onlinelearning.models.content;

/**
 * Lesson acts as a flyweight: intrinsic state (title, mediaType, durationMinutes)
 * is shared, while extrinsic state (studentId, progress) is provided by the caller.
 */
public class Lesson implements LearningComponent {

    private final String title;
    private final String mediaType;
    private final int durationMinutes;

    public Lesson(String title, String mediaType, int durationMinutes) {
        this.title = title;
        this.mediaType = mediaType;
        this.durationMinutes = durationMinutes;
    }

    @Override
    public void render(String studentId, double progressPercentage) {
        System.out.println(
                "[LessonFlyweight] '" + title + "' (" + mediaType + ", " + durationMinutes + " min) "
                        + "for student " + studentId + " at " + progressPercentage + "% progress");
    }
}
