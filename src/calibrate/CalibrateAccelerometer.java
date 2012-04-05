package calibrate;

import sensors.Accelerometer;

public class CalibrateAccelerometer extends Calibrate implements Runnable{

	static String className = "CalibrateAccelerometer";
	int counter = 0;
	int n;
	double target;
	Accelerometer cbc = new Accelerometer();
	
	CalibrateAccelerometer() {
		n = 100;
		target = 9.8e3;// mm/sec^2
	}
	CalibrateAccelerometer(int n, double target){
		this.n = n;
		this.target = target;
	}

	public String calibrate() {
		String result = "" + cbc.getAccel('Z');
		return result;
	}
}
