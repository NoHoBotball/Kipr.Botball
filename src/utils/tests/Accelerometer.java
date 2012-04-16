package utils.tests;

import java.io.FileNotFoundException;

public class Accelerometer extends AbstractTest{

	String className = "Accelerometer";
	
	utils.sensors.SimulatedAccelerometer cbc = new utils.sensors.SimulatedAccelerometer();
	double target;
	String axis;
	
	public Accelerometer() throws FileNotFoundException {
		super("Accelerometer", new String[] {"Target", "Result", "X", "Y", "Z"}, 100);
		className = "Accelerometer";
		target = 9.8e3;// mm/sec^2
		axis = "xyz";
	}
	public Accelerometer(String[] labels, int n, double target, String axis) throws FileNotFoundException {
		super("Accelerometer", labels, n);
		this.target = target;
		this.axis = axis;
	}
	public Accelerometer(String testName, String[] labels, int n, double target, String axis) throws FileNotFoundException {
		super(testName, labels, n);
		this.target = target;
		this.axis = axis;
	}
	

	Double target() {
		return target;
	}
	Double result() {
		double result = 0.0;
		
		X(); Y(); Z();
		
		for(int axis = 0; axis < 3; axis++) {
			result += data[axis + 2][this.i];
		}
		result = Math.sqrt(result);
		
		return result;
	}
	
	Double X() {
		data[2][n] = 0.0;
		if(axis.contains("x")){
			data[2][n] = cbc.getAccel('x');
		}
		return data[2][n];
	}
	Double Y() {
		data[3][n] = 0.0;
		if(axis.contains("x")){
			data[3][n] = cbc.getAccel('x');
		}
		return data[3][n];
	}
	Double Z() {
		data[4][n] = 0.0;
		if(axis.contains("x")){
			data[4][n] = cbc.getAccel('x');
		}
		return data[4][n];
	}
}