package utils;

public class Constants {
	
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
	 * Converts an inch amount to centimeters.
	 * 
	 * @param inches
	 * @return centimeters
	 */
	public static double inchesToCentimeters(double inches) {
		return 2.54 * inches;
	}
		
	public static enum Location {
		BLOCK_FENCE,
		BLOCK_CORNER,
		BLOCK_SIDE,
		GATHER_START,
		GAME_START;
		
		private static final Location[] blockLocations = new Location[] {BLOCK_FENCE, BLOCK_CORNER, BLOCK_SIDE};
		
		public static Location[] getBlockLocations() {
			return blockLocations;
		}
	}
	
	public static enum Direction {
		CENTER,
		NORTH (90),
		SOUTH (270),
		EAST (0),
		WEST (180);
		
		private int heading;
		
		Direction() {
			this.heading = -1;
		}
		
		Direction(int heading) {
			this.heading = heading;
		}
		
		public int getHeading() {
			return heading;
		}
		
		public int degreesTo(Direction d) {
			return d.getHeading() - this.getHeading();
		}
	}
	
}
