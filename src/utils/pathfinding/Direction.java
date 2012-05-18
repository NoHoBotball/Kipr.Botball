package utils.pathfinding;

public enum Direction {
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
