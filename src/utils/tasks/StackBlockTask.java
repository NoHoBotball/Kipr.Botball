package utils.tasks;

public class StackBlockTask extends Task{	
	private static StackBlockTask task = new StackBlockTask();
	
	public static StackBlockTask getTask() {
		return task;
	}
}
