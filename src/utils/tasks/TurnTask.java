package utils.tasks;

import utils.GameConstants;


public class TurnTask extends Task implements GameConstants {
	private static final TurnTask TURN_CW = new TurnTask(-90, STANDARD_SPEED);
	private static final TurnTask TURN_CCW = new TurnTask(90, STANDARD_SPEED);

	private int angle;
	private int speed;

	public static TurnTask turn(int degrees) {
		return new TurnTask(degrees, STANDARD_SPEED);
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
