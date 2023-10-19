package com.jaax.singletonpattern;

import com.jaax.singletonpattern.nosingleton.EventLoggerNoSingleton;
import com.jaax.singletonpattern.singleton.EventLoggerEnumSingleton;
import com.jaax.singletonpattern.singleton.EventLoggerLazySingleton;
import com.jaax.singletonpattern.singleton.EventLoggerMultithreadSafe;
import com.jaax.singletonpattern.singleton.EventLoggerSerializableSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@SpringBootApplication
public class SingletonPatternApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SingletonPatternApplication.class, args);
		// No singleton test
		noSingletonExample();

		// Lazy singleton test
		lazySingletonTest();

		//Serializable singleton test
		serializableSingletonTest();

		// Multithreadsafe singleton test
		multiThreadSingletonTest();

		//Reflection Example
		reflectionExample();
	}


	public static void noSingletonExample(){
		EventLoggerNoSingleton loggerNoSingleton = new EventLoggerNoSingleton();
		System.out.println("loggerNoSingleton.hashCode() = " + loggerNoSingleton.hashCode());
		EventLoggerNoSingleton loggerNoSingleton2 = new EventLoggerNoSingleton();
		System.out.println("loggerNoSingleton2.hashCode() = " + loggerNoSingleton2.hashCode());
		
		loggerNoSingleton.logEvent("Evento desde logger no singleton");
		loggerNoSingleton2.logEvent("Evento desde logger no singleton");
	}

	public static void lazySingletonTest() throws IOException, ClassNotFoundException {
		EventLoggerLazySingleton lazySingleton = EventLoggerLazySingleton.getInstance();
		System.out.println("lazySingleton.hashCode() = " + lazySingleton.hashCode());
		EventLoggerLazySingleton lazySingleton2 = EventLoggerLazySingleton.getInstance();
		System.out.println("lazySingleton2.hashCode() = " + lazySingleton2.hashCode());

		// Breaking lazy Singleton
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.obj"));
		objectOutputStream.writeObject(lazySingleton);
		objectOutputStream.close();

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.obj"));
		EventLoggerLazySingleton deserializedLazy = (EventLoggerLazySingleton) objectInputStream.readObject();
		objectInputStream.close();

		System.out.println("lazySingleton.hashCode() = " + lazySingleton.hashCode());
		System.out.println("deserializedLazy.hashCode() = " + deserializedLazy.hashCode());

	}

	public static void serializableSingletonTest() throws IOException, ClassNotFoundException {
		EventLoggerSerializableSingleton serializableSingleton = EventLoggerSerializableSingleton.getInstance();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.obj"));
		objectOutputStream.writeObject(serializableSingleton);
		objectOutputStream.close();

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.obj"));
		EventLoggerSerializableSingleton deserializedSerializable = (EventLoggerSerializableSingleton) objectInputStream.readObject();
		objectInputStream.close();

		System.out.println("serializableSingleton.hashCode() = " + serializableSingleton.hashCode());
		System.out.println("deserializedSerializable.hashCode() = " + deserializedSerializable.hashCode());

	}

	public static void multiThreadSingletonTest() throws Exception {
		EventLoggerMultithreadSafe multithreadSafe = EventLoggerMultithreadSafe.getInstance();
		System.out.println("multithreadSafe.hashCode() = " + multithreadSafe.hashCode());
		EventLoggerMultithreadSafe multithreadSafe2 = EventLoggerMultithreadSafe.getInstance();
		System.out.println("multithreadSafe2.hashCode() = " + multithreadSafe2.hashCode());
		multithreadSafe.logEvent("Test desde multithread safe");
		
	}

	public static void reflectionExample() throws Exception {
		EventLoggerMultithreadSafe multithreadSafe = EventLoggerMultithreadSafe.getInstance();
		System.out.println("multithreadSafe.hashCode() = " + multithreadSafe.hashCode());

		Class<?> singletonClass = EventLoggerMultithreadSafe.class;

		Field instaceField = singletonClass.getDeclaredField("instance");
		instaceField.setAccessible(true);

		Constructor<?> constructor = singletonClass.getDeclaredConstructor();
		constructor.setAccessible(true);
		
		EventLoggerMultithreadSafe newInstance = (EventLoggerMultithreadSafe) constructor.newInstance();
		instaceField.set(null,newInstance);

		EventLoggerMultithreadSafe secondInstance = EventLoggerMultithreadSafe.getInstance();
		System.out.println("secondInstance.hashCode() = " + secondInstance.hashCode());

		EventLoggerEnumSingleton.INSTANCE.logEvent("Hola");
	}

}
