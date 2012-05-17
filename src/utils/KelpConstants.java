package utils;

import cbc.movement.efficiency.regression.LSRLEfficiencyCalibrator;

public interface KelpConstants {
	double[] target = {10.1, 8.96, 5.13, 13.94, 8.4, 19.87, 2.48, 10, 8.76, 3.68};
	double[] actual = {11.875, 10.125, 5.875, 16.1, 9.75, 22.625, 3, 11.55, 15.125, 4.125};//.671927625
	LSRLEfficiencyCalibrator calibrator = new LSRLEfficiencyCalibrator(.7, 1.132618526, .9632716971);//target, actual);
	public static final int WHEEL_RIGHT_PORT = 0; 
	public static final int WHEEL_LEFT_PORT = 3;
	public static final double WHEEL_DIAMETER = 5.5;
	public static final double WHEEL_CIRCUMFERENCE = Math.PI*WHEEL_DIAMETER;
	public static final double WHEEL_DISTANCE = Conversions.inToCm(4.75);//4.75);5.3125
	public static final double ROBOT_TURN_CIRCUMFERENCE = WHEEL_DISTANCE*Math.PI;
	public static final int SERVO_RIGHT_PORT = 0;
	public static final int SERVO_LEFT_PORT = 3;
	public static final int ARM_MOTOR_PORT = 1;
	public static final int ET_PORT = 0;
	public static final int ARM_TOUCH_PORT = 8;
	public static final int ET_STOP_VALUE = 400;
	public static final int STANDARD_KELP_SPEED = 10;

}
