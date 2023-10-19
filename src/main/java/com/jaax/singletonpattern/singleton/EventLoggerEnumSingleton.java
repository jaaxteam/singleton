package com.jaax.singletonpattern.singleton;

public enum EventLoggerEnumSingleton {
    INSTANCE;
    public void logEvent(String event){
        System.out.println("Event = " + event);
    }
}
