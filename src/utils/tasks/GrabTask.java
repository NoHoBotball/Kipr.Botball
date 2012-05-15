 package utils.tasks;

public class GrabTask extends Task {
	
	private static GrabTask task = new GrabTask();
	
	public static GrabTask getTask() {
		return task;
	}
	
	private GrabTask () {}
	
}
