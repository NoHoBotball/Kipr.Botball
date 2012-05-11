 package utils.pathfinding;

public class ReleaseTask extends Task {
	
	private static ReleaseTask task = new ReleaseTask();
	
	public static ReleaseTask getTask() {
		return task;
	}
	
	private ReleaseTask () {}
	
}
