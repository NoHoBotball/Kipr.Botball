package utils.tasks;

import utils.GameConstants;

public class SlowDriveTask extends Task implements GameConstants {
	
	private double distance;
	private double speed;
	private double slowDistance;
	private static double default_slow_distance = Double.NaN;
	private static double default_speed = Double.NaN;
	
	
	public SlowDriveTask (double distance, double speed, double slowDistance) {
		this.distance = distance;
		this.speed = speed;
		this.slowDistance = slowDistance;
		default_slow_distance = slowDistance;
		default_speed = speed;
	}
	
	public SlowDriveTask (double distance, double speed) throws TaskException {
		if(default_slow_distance == Double.NaN) throw new TaskException("You must set a default slow distance.");
		this.distance = distance;
		this.speed = speed;
		this.slowDistance = default_slow_distance;
		default_speed = speed;
	}
	
	public SlowDriveTask (double distance) throws TaskException {
		if(default_speed == Double.NaN) throw new TaskException("You must set a default speed.");
		if(default_slow_distance == Double.NaN) throw new TaskException("You must set a default slow distance.");
		this.distance = distance;
		this.slowDistance = default_slow_distance;
		this.speed = default_speed;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double getSlowDistance() {
		return slowDistance;
	}
}
