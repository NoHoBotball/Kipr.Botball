package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import cbccore.events.Event;
import cbccore.events.EventListenerAdapter;
import cbccore.events.EventManager;
import cbccore.events.EventType;

public abstract class TestEvent {
	
	static String testName;
	File dataFile;
	PrintWriter dataStream;
	int counter, n, i = 0;
	boolean run = true;
	
	EventManager manager = EventManager.get();
	public static EventType doTest = new EventType();
	public static EventType endTest = new EventType();
	
	public TestEvent(int n) throws FileNotFoundException {
		counter = counter();
		dataFile = new File(testName + "_" + counter + ".test");		
		dataStream = new PrintWriter(dataFile);
		this.n = n;
	}

	public void main() {
		dataStream.println("target" + "\t\t" + "result");
		manager.connect(doTest, doTestHandler);
		manager.connect(endTest, endTestHandler);
		while(run) {
			doTestCall();
		}
	}
	
	public void doTestCall(){
		
	}
	
	public String target() {
		return null;
	}
	public String result() {
		return null;
	}
	
	EventListenerAdapter doTestHandler = new EventListenerAdapter(){
		@SuppressWarnings("rawtypes")
		public void event(Event doTestEvent){
			if(i<n){
				dataStream.println(target() + "\t\t" + result());
				i++;
			}
		}
	};
	
	EventListenerAdapter endTestHandler = new EventListenerAdapter(){
		@SuppressWarnings("rawtypes")
		public void event(Event endTestEvent){
			if(i<n){
				manager.disconnect(doTest, doTestHandler);
				manager.disconnect(endTest, endTestHandler);
				run = false;
			}
		}
	};
	
	public int counter() throws FileNotFoundException {
		File counterSource = new File(testName + "_counter");
		PrintWriter counterW = new PrintWriter(counterSource);
		Scanner counterR = new Scanner(counterSource);
		
		if(counterR.hasNextInt())	counter = counterR.nextInt();
		else						counter = 0;
		counterR.close();
		counterW.println(++counter);
		counterW.flush();
		counterW.close();
		return counter;
	}
}
