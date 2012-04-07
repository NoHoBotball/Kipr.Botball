package test;

import java.io.FileNotFoundException;

public class Accelerometer extends Test{

	String className = "Accelerometer";
	
	sensors.Accelerometer cbc = new sensors.Accelerometer();
	double target;
	char axis;
	
	public Accelerometer() throws FileNotFoundException {
		super("Accelerometer", 100);
		className = "Accelerometer";
		target = 9.8e3;// mm/sec^2
		axis = 'z';
	}
	public Accelerometer(int n, double target, char axis) throws FileNotFoundException {
		super("Accelerometer", n);
		this.target = target;
		this.axis = axis;
	}
	public Accelerometer(String testName, int n, double target, char axis) throws FileNotFoundException {
		super(testName, n);
		this.target = target;
		this.axis = axis;
	}
	

	protected Double target() {
		return target;
	}
	protected Double result() {
		return cbc.getAccel(axis);
	}
	
	protected void doTestCall() {
		doTest();
	}
}
