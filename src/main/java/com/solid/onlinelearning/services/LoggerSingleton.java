package com.solid.onlinelearning.services;

public class LoggerSingleton {

    // Единичный экземпляр
    private static LoggerSingleton instance;

    // Закрытый конструктор, чтобы предотвратить создание других экземпляров
    private LoggerSingleton() {}

    // Метод для получения единственного экземпляра
    public static LoggerSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggerSingleton.class) {
                if (instance == null) {
                    instance = new LoggerSingleton();
                }
            }
        }
        return instance;
    }

    // Метод для логирования
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
