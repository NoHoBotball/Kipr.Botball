package utils.tasks;

import utils.pathfinding.Direction;



public class TurnTask extends Task {
	
	private double angle;
	private double speed;
	
	private static double default_speed = Double.NaN;

	public static TurnTask turnCW() throws TaskException {
		return new TurnTask( 90, default_speed);
	}
	
	public static TurnTask turnAround() throws TaskException {
		return new TurnTask( 180, default_speed);
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
	
	public static TurnTask turnAround(double speed) throws TaskException {
		return new TurnTask( 180, speed);
	}

	public TurnTask (double angle, double speed) throws TaskException {
		this.angle = angle;
		this.speed = default_speed = speed;
	}
	
	public TurnTask (Direction from, Direction to, double speed) throws TaskException {
		this(from.degreesTo(to), speed);
	}
	
	public TurnTask (Direction from, Direction to) throws TaskException {
		this(from.degreesTo(to));
	}
	
	public TurnTask (double angle) throws TaskException {
		this(angle , default_speed);
		if(default_speed == Double.NaN) throw new TaskException("Default_speed must be set before it is used");
	}

	public double getAngle() {
		return angle;
	}

	public double getSpeed() {
		return speed;
	}
}
