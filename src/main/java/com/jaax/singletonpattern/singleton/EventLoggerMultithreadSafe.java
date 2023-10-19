package com.jaax.singletonpattern.singleton;

public class EventLoggerMultithreadSafe {

    private static EventLoggerMultithreadSafe instance = null;

    private EventLoggerMultithreadSafe() throws Exception {
        /*if(instance != null){
            throw new Exception("La instancia ha sido creada");
        }*/
    }

    public static EventLoggerMultithreadSafe getInstance() throws Exception {
        if(instance == null){
            synchronized (EventLoggerMultithreadSafe.class){
                if(instance == null){
                    instance = new EventLoggerMultithreadSafe();
                }
            }
        }
        return instance;
    }

    public void logEvent(String event){
        System.out.println("Event = " + event);
    }
    
}
