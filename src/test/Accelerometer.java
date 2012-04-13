package test;

import java.io.FileNotFoundException;

public class Accelerometer extends AbstractTest{

	String className = "Accelerometer";
	
	sensors.SimulatedAccelerometer cbc = new sensors.SimulatedAccelerometer();
	double target;
	String axis;
	
	public Accelerometer() throws FileNotFoundException {
		super("Accelerometer", 100);
		className = "Accelerometer";
		target = 9.8e3;// mm/sec^2
		axis = "xyz";
	}
	public Accelerometer(int n, double target, String axis) throws FileNotFoundException {
		super("Accelerometer", n);
		this.target = target;
		this.axis = axis;
	}
	public Accelerometer(String testName, int n, double target, String axis) throws FileNotFoundException {
		super(testName, n);
		this.target = target;
		this.axis = axis;
	}
	

	protected Double target() {
		return target;
	}
	protected Double result() {
		double x = 0.0;
		double y = 0.0;
		double z = 0.0;
		if(axis.contains("x")) x = cbc.getAccel('x');
		if(axis.contains("y")) y = cbc.getAccel('y');
		if(axis.contains("z")) z = cbc.getAccel('z');

		return Math.sqrt(x*x + y*y + z*z);
	}
}