package utils.pathfinding;

import utils.Constants;


public class TurnTask implements Task {
	private static final TurnTask TURN_CW = new TurnTask(-90, Constants.STANDARD_SPEED);
	private static final DriveTask BETWEEN_CUBES_LONG = new DriveTask(Constants.CUBE_WIDTH/2 + Constants.CUBE_DISTANCE - Constants.BOT_OFFSET, Constants.STANDARD_SPEED);
	private static final DriveTask BETWEEN_CUBES_SHORT = new DriveTask(Constants.CUBE_DISTANCE - Constants.BOT_OFFSET*2, Constants.STANDARD_SPEED);
	private static final DriveTask TO_CENTER_LONG = new DriveTask(Constants.CUBE_WIDTH + Constants.CUBE_DISTANCE, Constants.STANDARD_SPEED);
	private static final DriveTask TO_CENTER_SHORT = new DriveTask(Constants.CUBE_WIDTH/2 + Constants.CUBE_DISTANCE, Constants.STANDARD_SPEED);
	
	private int angle;
	private int speed;
	
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
