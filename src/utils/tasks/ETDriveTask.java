package utils.tasks;


public class ETDriveTask extends Task{

	private double speed;
	private int ETValue;

	public ETDriveTask( double speed, int ETValue) {
		this.speed = speed;
		this.ETValue = ETValue;
	}
	
	
	public double getSpeed() {
		return speed;
	}
	
	public int getETValue() {
		return ETValue;
	}

}
