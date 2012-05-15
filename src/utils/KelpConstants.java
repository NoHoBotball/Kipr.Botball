package utils;

public interface KelpConstants {
	public static final int WHEEL_RIGHT_PORT = 3; 
	//Was determining L/R at perspective with claw forward. JVM does it the other way, so I switched it to right 3 left 0.
	public static final int WHEEL_LEFT_PORT = 0;
	public static final double WHEEL_DIAMETER = 5.5;
	public static final double WHEEL_CIRCUMFERENCE = Math.PI*WHEEL_DIAMETER;
	public static final double WHEEL_DISTANCE = 14;
	public static final int SERVO_RIGHT_PORT = 0;
	public static final int SERVO_LEFT_PORT = 3;
	public static final int ARM_MOTOR_PORT = 1;
	public static final int ET_PORT = 0;
	public static final int ARM_TOUCH_PORT = 8;
	public static final int ET_STOP_VALUE = 400;
	public static final double EFFICIENCY_VALUE =  1.16;
	public static final int STANDARD_KELP_SPEED = 10;

}
