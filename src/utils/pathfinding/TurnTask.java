package utils.pathfinding;

import utils.Constants;


public class TurnTask extends Task {
	private static final TurnTask TURN_CW = new TurnTask(-90, Constants.STANDARD_SPEED);
	private static final TurnTask TURN_CCW = new TurnTask(90, Constants.STANDARD_SPEED);

	private static int angle;
	private static int speed;

	public static TurnTask turnCW() {
		angle = 90;
		speed = Constants.STANDARD_KELP_SPEED;
		return TURN_CW;
	}

	public static TurnTask turnCCW() {
		angle = -90;
		speed = Constants.STANDARD_KELP_SPEED;
		return TURN_CCW;
	}

	public TurnTask (int angle, int speed) {
		TurnTask.angle = angle;
		TurnTask.speed = speed;
	}


	public int getAngle() {
		return angle;
	}

	public int getSpeed() {
		return speed;
	}

}
