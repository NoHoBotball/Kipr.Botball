package utils.tasks;

import utils.GameConstants;

public class DriveTask extends Task implements GameConstants {
	
	private static final DriveTask HALF_CUBE = new DriveTask(CUBE_WIDTH/2, STANDARD_SPEED);
	private static final DriveTask BETWEEN_CUBES_LONG = new DriveTask(CUBE_WIDTH/2 + CUBE_DISTANCE - BOT_OFFSET, STANDARD_SPEED);
	private static final DriveTask BETWEEN_CUBES_SHORT = new DriveTask(CUBE_DISTANCE - BOT_OFFSET*2, STANDARD_SPEED);
	private static final DriveTask TO_CENTER_LONG = new DriveTask(CUBE_WIDTH + CUBE_DISTANCE, STANDARD_SPEED);
	private static final DriveTask TO_CENTER_SHORT = new DriveTask(CUBE_WIDTH/2 + CUBE_DISTANCE, STANDARD_SPEED);
	
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
	private double speed;
	private double default_speed = 0;
	
	
	public DriveTask (double distance, int speed) {
		this.distance = distance;
		this.speed = speed;
		default_speed = speed;
	}
	
	public DriveTask (double distance) {
		this.distance = distance;
		this.speed = default_speed;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getSpeed() {
		return speed;
	}
}
