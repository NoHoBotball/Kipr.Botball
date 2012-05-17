package utils;

public interface BlockConstants extends GameConstants{
	/* ------- Measurements ------- */
	/* ----All values in inches----*/
	

	
	/**
	 * The distance the bot is from a block when begins the grab sequence.
	 * Also the distance from the ET_Sensor to the edge of the claw.
	 */
	public static final double CLAW_FREE= 0.5; // TODO: Set to correct value
	
	/**
	 * How off center the claw is.
	 */
	public static final double CLAW_OFFSET = 0;
	
	/**
	 * 
	 */
	public static final double GRAB_DIST = 2.5;//inches
	public static final double DROP_DIST = 3.5;//inches
	
	public static final double CUBE_DIST_LONG = CUBE_DIST + CUBE;
	public static final double CUBE_DIST_SHORT = CUBE_DIST + CUBE/2 - CLAW_FREE;
	
	/* ------- Tweakable Settings ------- */
	
	/**
	 * The default all-around speed in cm/s. Should probably be removed later; use as a stand-in unit for now.
	 */
	public static final int STANDARD_TURN_SPEED = 30;
	public static final int STANDARD_SPEED = -20;
	public static final int APPROACH_SPEED = -10;
	public static final int RETREAT_SPEED  = -40;
	
	public static final int STANDARD_ET_VALUE = 0;
	public static final int APPROACH_ET_VALUE = 0;
	public static final int RETREAT_ET_VALUE = 0;


	
}
