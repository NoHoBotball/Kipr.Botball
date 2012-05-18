package utils;

public interface GameConstants {

	/* ------- Measurements ------- */
	
	/**
	 * The width of each cube in inches.
	 */
	public static final double CUBE = 4;
	
	/**
	 * The distance between the edges of each cube in inches.
	 */
	public static final double CUBE_DIST = 22;
	
	/**
	 * The distance to robot has to move to be in line with the first block.
	 */
	public static final double STARTING_DISTANCE_VERTICAL = 48; // TODO: Set to correct value
	
	/**
	 * The distance the robot must travel to be one BOT_OFFSET away from the first block.
	 */
	public static final double STARTING_DISTANCE_HORIZONTAL = 30; // TODO: Set to correct value
	
	/**
	 * The distance the robot must travel SOUTH from EAST of CENTER to get to STACK.
	 */
	public static final double STACK_DIST = 20;
	
	/**
	 * The width of the entire game board in inches.
	 */
	public static final double GAME_BOARD_WIDTH = 96;
	}
