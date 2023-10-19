package com.jaax.singletonpattern.nosingleton;

public class EventLoggerNoSingleton {
    public EventLoggerNoSingleton() {
    }

    public void logEvent(String event)
    {
        System.out.println("Event = " + event);
    }
}
