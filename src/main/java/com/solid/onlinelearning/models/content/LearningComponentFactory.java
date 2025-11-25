package com.solid.onlinelearning.models.content;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory/cache for flyweights so that identical lessons reuse the same instance.
 */
public class LearningComponentFactory {

    private final Map<String, LearningComponent> cache = new HashMap<>();

    public LearningComponent getLesson(String title, String mediaType, int durationMinutes) {
        String key = title + "|" + mediaType + "|" + durationMinutes;
        return cache.computeIfAbsent(key, unused -> new Lesson(title, mediaType, durationMinutes));
    }

    public int cachedFlyweightsCount() {
        return cache.size();
    }
}
