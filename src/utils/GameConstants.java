package utils;

public interface GameConstants {
	/* ------- Tweakable Settings ------- */
	
	
	
	/**
	 * The default all-around speed in cm/s. Should probably be removed later; use as a stand-in unit for now.
	 */
	public static final int STANDARD_SPEED = 20;
	
	/* ------- Measurements ------- */
	
	/**
	 * The distance the bot is from a block when it picks it up.
	 */
	public static final double BOT_OFFSET = 0.5; // TODO: Set to correct value
	
	/**
	 * The distance to robot has to move to be in line with the first block.
	 */
	public static final double STARTING_DISTANCE_VERTICAL = 48; // TODO: Set to correct value
	
	/**
	 * The distance the robot must travel to be one BOT_OFFSET away from the first block.
	 */
	public static final double STARTING_DISTANCE_HORIZONAL = 30; // TODO: Set to correct value
	
	/**
	 * The width of each cube in inches.
	 */
	public static final double CUBE_WIDTH = 4;
	
	/**
	 * The distance between the edges of each cube in inches.
	 */
	public static final double CUBE_DISTANCE = 22;
	
	/**
	 * The width of the entire game board in inches.
	 */
	public static final double GAME_BOARD_WIDTH = 96;
	
	

	/**
	 * The width of the entire game board in inches.
	 */
	public static final double _GAME_BOARD_WIDTH = 96;
}
