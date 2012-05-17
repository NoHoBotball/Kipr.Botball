package utils.tasks;



public class TurnTask extends Task {
	
	private double angle;
	private double speed;
	private double radius;
	private boolean isArcTurn;

	private static double default_speed;

	public static TurnTask turnCW() {
		return new TurnTask( 90, default_speed);
	}
	
	public static TurnTask turnAround() {
		return new TurnTask( 180, default_speed);
	}

	public static TurnTask turnCCW() {
		return new TurnTask(-90, default_speed);
	}
	
	public static TurnTask turnCW(double speed) {
		return new TurnTask( 90, speed);
	}

	public static TurnTask turnCCW(double speed) {
		return new TurnTask(-90, speed);
	}
	
	public static TurnTask turnAround(double speed) {
		return new TurnTask( 180, speed);
	}

	public TurnTask (double angle, double speed) {
		this.angle = angle;
		this.speed = speed;
		this.isArcTurn = false;
		default_speed = speed;
	}
	
	public TurnTask (double angle) {
		this.angle = angle;
		this.isArcTurn = false;
		this.speed = default_speed;
	}
	
	public TurnTask (double angle, double speed, double radius){
		this.angle = angle;
		this.speed = speed;
		this.radius = radius;
		this.speed = default_speed;
		this.isArcTurn = true;
	}

	public double getAngle() {
		return angle;
	}

	public double getSpeed() {
		return speed;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public boolean isArcTurn(){
		return isArcTurn;
	}
}
