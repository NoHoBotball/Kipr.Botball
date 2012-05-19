package utils.pathfinding;

public enum Location {
	//BlockRobot
	BLOCK_FENCE,
	BLOCK_CORNER,
	BLOCK_SIDE,
	STACK,
	CENTER,
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
