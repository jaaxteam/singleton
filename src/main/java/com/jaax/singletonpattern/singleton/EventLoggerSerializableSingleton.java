package com.jaax.singletonpattern.singleton;

import java.io.Serializable;

public class EventLoggerSerializableSingleton implements Serializable {
    private static EventLoggerSerializableSingleton instance = null;

    private EventLoggerSerializableSingleton() {
    }

    public static EventLoggerSerializableSingleton getInstance(){
        if(instance == null){
            instance = new EventLoggerSerializableSingleton();
        }
        return instance;
    }

    public void logEvent(String event){
        System.out.println("Event = " + event);
    }

    protected Object readResolve(){
        return instance;
    }
}
