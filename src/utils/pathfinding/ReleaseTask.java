 package utils.pathfinding;

public class ReleaseTask implements Task {
	
	private static ReleaseTask task = new ReleaseTask();
	
	public static ReleaseTask getTask() {
		return task;
	}
	
	private ReleaseTask () {}
	
}
