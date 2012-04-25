package utils;

public class Constants {
	
	/* ------- Tweakable Settings ------- */
	
	public static final int WHEEL_RIGHT_PORT = 3;
	
	public static final int WHEEL_LEFT_PORT = 0;
	
	public static final double WHEEL_DIAMETER = 5;
	
	public static final double WHEEL_CIRCUMFERENCE = Math.PI*WHEEL_DIAMETER;
	
	public static final double WHEEL_DISTANCE = 5;
	
	public static final int SERVO_RIGHT_PORT = 3;
	
	public static final int SERVO_LEFT_PORT = 0;
	
	public static final int ARM_MOTOR_PORT = 1;
	
	public static final int ET_PORT = 0;
	
	public static final int ARM_TOUCH_PORT = 8;
	
	public static final int STANDARD_KELP_SPEED = 800;
	
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
		//BlockRobot
		BLOCK_FENCE,
		BLOCK_CORNER,
		BLOCK_SIDE,
		GATHER_START,
		GAME_START,
		//KelpRobot
		FIRST_KELP,
		SECOND_KELP,
		DROPOFF
		;
		
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
