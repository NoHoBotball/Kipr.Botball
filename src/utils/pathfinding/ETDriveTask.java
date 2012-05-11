package utils.pathfinding;

public class ETDriveTask  {

	private double distance;
	private int speed;
	private int ETValue;

	public ETDriveTask(double distance, int speed, int ETValue) {
		this.distance = distance;
		this.speed = speed;
		this.ETValue = ETValue;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double getETValue() {
		return ETValue;
	}

}
