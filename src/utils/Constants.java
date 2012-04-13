package utils;

public class Constants {
	
	/* ------- Tweakable Settings ------- */
	
	/**
	 * The default all-around speed in cm/s. Should probably be removed later; use as a stand-in unit for now.
	 */
	public static final int STANDARD_SPEED = 10;
	
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
	
	/* ------- Block Data ------- */
	
	private static Block[] blockOrder = new Block[3];
	
	/**
	 * Sets the color of a specified block.
	 * 
	 * Block 0 is the block near the fence, 1 is the corner block, 2 is the block near the board edge.
	 * 
	 * @param index block index
	 * @param b block color to use
	 */
	public static void setBlock(int index, Block b) {
		if (index < 0 || index > 2)
			throw new IllegalArgumentException("Block index cannot be greater than 2.");
		if (blockOrder[index] != null)
			throw new RuntimeException("The first block was already set.");
		blockOrder[index] = b;
	}
	
	/**
	 * Gets the color of a specified block.
	 * 
	 * Block 0 is the block near the fence, 1 is the corner block, 2 is the block near the board edge.
	 * 
	 * @param index block index
	 * @return block color
	 */
	public static Block getBlock(int index) {
		if (index < 0 || index > 2)
			throw new IllegalArgumentException("Block index cannot be greater than 2.");
		return blockOrder[index];
	}
	
	/**
	 * Represents the various block types.
	 */
	public static enum Block {
		RED (0),
		YELLOW (2),
		BLUE (3);
		
		private int channel;
		
		Block (int channel) {
			this.channel = channel;
		}
		
		/**
		 * Gets the channel number for the CBC camera's color tracking.
		 * 
		 * @return channel number
		 */
		public int getChannel() {
			return channel;
		}
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
		CENTER,
		NORTH,
		SOUTH,
		EAST,
		WEST;
	}
	
}
