package utils.tasks;


public class ETDriveTask extends Task{

	private int speed;
	private int ETValue;

	public ETDriveTask( int speed, int ETValue) {
		this.speed = speed;
		this.ETValue = ETValue;
	}
	
	
	public double getSpeed() {
		return speed;
	}
	
	public double getETValue() {
		return ETValue;
	}

}
