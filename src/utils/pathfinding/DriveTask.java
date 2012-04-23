package utils.pathfinding;

import utils.Constants;

public class DriveTask implements Task {
	
	private static final DriveTask HALF_CUBE = new DriveTask(Constants.CUBE_WIDTH/2, Constants.STANDARD_SPEED);
	private static final DriveTask BETWEEN_CUBES_LONG = new DriveTask(Constants.CUBE_WIDTH/2 + Constants.CUBE_DISTANCE - Constants.BOT_OFFSET, Constants.STANDARD_SPEED);
	private static final DriveTask BETWEEN_CUBES_SHORT = new DriveTask(Constants.CUBE_DISTANCE - Constants.BOT_OFFSET*2, Constants.STANDARD_SPEED);
	private static final DriveTask TO_CENTER_LONG = new DriveTask(Constants.CUBE_WIDTH + Constants.CUBE_DISTANCE, Constants.STANDARD_SPEED);
	private static final DriveTask TO_CENTER_SHORT = new DriveTask(Constants.CUBE_WIDTH/2 + Constants.CUBE_DISTANCE, Constants.STANDARD_SPEED);
	private static final DriveTask EXIT_STARTING_BOX = new DriveTask(30, Constants.STANDARD_SPEED_KELP);
	
	public static DriveTask getHalfCubeTask() {
		return HALF_CUBE;
	}
	
	public static DriveTask getMoveCubeDistanceTask(boolean isCentered) {
		return isCentered ? BETWEEN_CUBES_LONG : BETWEEN_CUBES_SHORT;
	}
	
	public static DriveTask getMoveToCenterTask(boolean isCentered) {
		return isCentered ? TO_CENTER_LONG : TO_CENTER_SHORT;
	}
	
	private double distance;
	private int speed;
	
	public DriveTask (double distance, int speed) {
		this.distance = distance;
		this.speed = speed;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getSpeed() {
		return speed;
	}

	public static DriveTask getExitSBDistanceTask() {
		// TODO Auto-generated method stub
		/*Do I need to make a DriveTask for every little movement, even if I only do those movements once in the 
		entire game? */
		return null;
	}
	
}
