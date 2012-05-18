package utils.tasks;

import utils.pathfinding.Direction;



public class TurnTask extends Task {
	
	private double angle; //in degrees
	private double speed; //in degrees per second

	private static double default_speed = Double.NaN;

	public static TurnTask turnCW() throws TaskException {
		return new TurnTask( 90, default_speed);
	}

	public static TurnTask turnCCW() throws TaskException {
		return new TurnTask(-90, default_speed);
	}
	
	public static TurnTask turnCW(double speed) throws TaskException {
		return new TurnTask( 90, speed);
	}

	public static TurnTask turnCCW(double speed) throws TaskException {
		return new TurnTask(-90, speed);
	}

	public TurnTask (double angle, double speed) throws TaskException {
		this.angle = angle;
		this.speed = default_speed = speed;
		while(angle > 180.) angle -= 360.;
		while(angle <-180.) angle += 360.;
	}
	
	public TurnTask (Direction from, Direction to, double speed) throws TaskException {
		this(from.degreesTo(to), speed);
	}
	
	public TurnTask (Direction from, Direction to) throws TaskException {
		this(from.degreesTo(to));
	}
	
	public TurnTask (double angle) throws TaskException {
		this(angle, default_speed);
		if(default_speed == Double.NaN) throw new TaskException("You must set a default speed.");
	}

	public double getAngle() {
		return angle;
	}

	public double getSpeed() {
		return speed;
	}

}
