package utils.pathfinding;

import utils.Constants;


public class TurnTask extends Task {
	private static final TurnTask TURN_CW = new TurnTask(-90, Constants.STANDARD_SPEED);
	private static final TurnTask TURN_CCW = new TurnTask(90, Constants.STANDARD_SPEED);

	private int angle;
	private int speed;

	public TurnTask turnCW() {
		return TURN_CW;
	}
	
	public TurnTask turnCCW() {
		return TURN_CCW;
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
