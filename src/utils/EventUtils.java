package utils;

import java.lang.reflect.Method;

import cbc.events.Event;
import cbc.events.EventListenerAdapter;
import cbc.events.EventManager;
import cbc.events.EventType;

public class EventUtils {
	
	private static EventManager manager = EventManager.get();
	
	/**
	 * Registers an event to call a specific function.
	 * 
	 * Automates the CBCJVM's event management process.
	 * The name of the function passed must be a valid function on the object
	 * passed as the delegate parameter. It must accept a single parameter, an Event.
	 * 
	 * @param type type of event to register
	 * @param delegate object to call function on
	 * @param function name of delegate function
	 */
	public static void registerEvent(EventType type, final Object delegate, final String function) {
		
		// Register the event
		manager.connect(type, new EventListenerAdapter() {
			// Use Java reflection to call the given method
			Class<? extends Object> clazz = delegate.getClass();
			// Get the passed method
			Method method;
			// Try to get the method; may fail if the method doesn't exist
			{
				try {
					method = clazz.getMethod(function, Event.class);
				} catch (Exception ex) {
					System.err.println("Attempted to register event on nonexistent function.");
					ex.printStackTrace();
				}
			}
			// Handle event
			@SuppressWarnings("rawtypes")
			@Override
			public void event(Event e) {
				try {
					// Attempt to invoke the method on the registered delegate
					method.invoke(delegate, e);
				} catch (Exception ex) {
					System.err.println("Error passing event to handler.");
					ex.printStackTrace();
				}
			}
		});
		
	}
	
}
