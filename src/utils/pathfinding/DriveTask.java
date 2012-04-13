package utils.pathfinding;

public class DriveTask extends Task {
	
	private int distance;
	private int speed;
	
	public DriveTask (int distance, int speed) {
		this.distance = distance;
		this.speed = speed;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public int getSpeed() {
		return speed;
	}
	
}
