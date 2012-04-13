package utils.pathfinding;

public class DriveTask extends Task {
	
	private double distance;
	private int speed;
	
	public DriveTask (double distance, int speed) {
		this.distance = distance;
		this.speed = speed;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getSpeed() {
		return speed;
	}
	
}
