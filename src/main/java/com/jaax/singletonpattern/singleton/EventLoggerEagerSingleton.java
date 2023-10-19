package com.jaax.singletonpattern.singleton;

public class EventLoggerEagerSingleton {
    private static EventLoggerEagerSingleton instance = new EventLoggerEagerSingleton();
    private EventLoggerEagerSingleton() {
    }

    public static EventLoggerEagerSingleton getInstance(){
        return instance;
    }
    public void logEvent(String event){
        System.out.println("Event = " + event);
    }

}
