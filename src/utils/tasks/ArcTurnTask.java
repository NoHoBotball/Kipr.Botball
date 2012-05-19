package utils.tasks;

import utils.pathfinding.Direction;



public class ArcTurnTask extends Task {

	private double angle;
	private double speed;
	private double radius;
	private static double default_speed = Double.NaN;


	public ArcTurnTask (double angle, double speed, double radius) throws TaskException {
		this.angle = angle;
		this.radius = radius;
		this.speed = default_speed = speed;

	}


	public double getAngle() {
		return angle;
	}

	public double getSpeed() {
		return speed;
	}

	public double getRadius() {
		return radius;
	}
}
