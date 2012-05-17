package utils.tasks;

import utils.pathfinding.Direction;



public class TurnTask extends Task {
	
	private double angle; //in degrees
	private double speed; //in degrees per second

	private static double default_speed = 10;

	public static TurnTask turnCW() {
		return new TurnTask( 90, default_speed);
	}

	public static TurnTask turnCCW() {
		return new TurnTask(-90, default_speed);
	}
	
	public static TurnTask turnCW(double speed) {
		return new TurnTask( 90, speed);
	}

	public static TurnTask turnCCW(double speed) {
		return new TurnTask(-90, speed);
	}

	public TurnTask (double angle, double speed) {
		this.angle = angle;
		this.speed = speed;
		default_speed = speed;
		while(angle > 180) angle -= 360;
		while(angle <-180) angle += 360;
	}
	
	public TurnTask (Direction from, Direction to, double speed) {
		new TurnTask(from.degreesTo(to), speed);
	}
	
	public TurnTask (Direction from, Direction to) {
		new TurnTask(from.degreesTo(to));
	}
	
	public TurnTask (double angle) {
		new TurnTask(angle, default_speed);
	}

	public double getAngle() {
		return angle;
	}

	public double getSpeed() {
		return speed;
	}

}
