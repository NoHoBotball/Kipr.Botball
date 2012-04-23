package utils;

public class Constants {
	
	/* ------- Tweakable Settings ------- */
	
	/**
	 * Wheel diameter of kelp bot in centimeters.
	 */
	
	public static final double WHEEL_DIAMETER = 5;
	
	/**
	 * Standard travel speed for kelpbot.
	 */
	
	public static final int STANDARD_SPEED_KELP = 700;
	
	/**
	 * The default all-around speed in cm/s. Should probably be removed later; use as a stand-in unit for now.
	 */
	public static final int STANDARD_SPEED = 300;
	
	/* ------- Measurements ------- */
	
	/**
	 * The distance the bot is from a block when it picks it up.
	 */
	public static final double BOT_OFFSET = 0.5; // TODO: Set to correct value
	
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
		
	public static double ticksToCentimeters(double ticks){
		return ((ticks/1100)*Math.PI*WHEEL_DIAMETER);
	}
	
	public static double centimetersToTicks(double centimeters){
		return (centimeters/(Math.PI*WHEEL_DIAMETER) * 1100);
	}
	
	public static enum BotLocation {
		BLOCK_FENCE,
		BLOCK_CORNER,
		BLOCK_SIDE,
		GATHER_START;
		
		private static final BotLocation[] blockLocations = new BotLocation[] {BLOCK_FENCE, BLOCK_CORNER, BLOCK_SIDE};
		
		public static BotLocation[] getBlockLocations() {
			return blockLocations;
		}
	}
	
	public static enum Direction {
		CENTER (-1),
		NORTH (90),
		SOUTH (270),
		EAST (0),
		WEST (180);
		
		private int heading;
		
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
