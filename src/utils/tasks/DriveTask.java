package utils.tasks;

import utils.GameConstants;

public class DriveTask extends Task implements GameConstants {
	
	private double distance;
	private double speed;
	private static double default_speed = Double.NaN;
	
	
	public DriveTask (double distance, double speed) {
		this.distance = distance;
		this.speed = speed;
		default_speed = speed;
	}
	
	public DriveTask (double distance) throws TaskException {
		if(default_speed == Double.NaN) throw new TaskException("You must set a default speed.");
		this.distance = distance;
		this.speed = default_speed;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getSpeed() {
		return speed;
	}
}
