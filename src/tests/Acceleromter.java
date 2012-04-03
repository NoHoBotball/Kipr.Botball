package tests;

import cbccore.low.Sensor;

public class Acceleromter {
	
	//a value of 1365 is approximately 1 G (9.8    m/sec^2 - accel of gravity)	
	//a value of 1365 is approximately 1 G (9.8e3 mm/sec^2 - accel of gravity)	
	
	final static double ACCEL_UNIT = 9.8E3 / 1365; // mm/sec^2

}
