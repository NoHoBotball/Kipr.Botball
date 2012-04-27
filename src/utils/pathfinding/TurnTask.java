package utils.pathfinding;

import utils.Constants;


public class TurnTask implements Task {
	private static final TurnTask TURN_CW = new TurnTask(-90, Constants.STANDARD_SPEED);
	private static final TurnTask TURN_CCW = new TurnTask(90, Constants.STANDARD_SPEED);
	
	private int angle;
	private int speed;
	
	public static TurnTask turn(int degrees) {
		return new TurnTask(degrees, Constants.STANDARD_SPEED);
	}
	
	public TurnTask (int angle, int speed) {
		this.angle = angle;
		this.speed = speed;
	}
		

	public int getAngle() {
		return angle;
	}
	
	public int getSpeed() {
		return speed;
	}
	
}
