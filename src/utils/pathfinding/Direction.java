package utils.pathfinding;

public enum Direction {
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
