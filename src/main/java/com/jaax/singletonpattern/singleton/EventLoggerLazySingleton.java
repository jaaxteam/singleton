package com.jaax.singletonpattern.singleton;

import java.io.Serializable;

public class EventLoggerLazySingleton implements Serializable {
    private static EventLoggerLazySingleton instance = null;

    private EventLoggerLazySingleton() {
    }

    public static EventLoggerLazySingleton getInstance(){
        if(instance == null){
            instance = new EventLoggerLazySingleton();
        }
        return instance;
    }

    public void logEvent(String event){
        System.out.println("Event = " + event);
    }

}
