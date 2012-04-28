package utils.pathfinding;

public class TurnTask extends Task {
	
	private int angle;
	private int speed;
	
	public TurnTask (int angle, int speed) {
		this.angle = angle;
		this.speed = speed;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public int getSpeed() {
		return speed;
	}
	
}
