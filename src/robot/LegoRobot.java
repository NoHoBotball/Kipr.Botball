package robot;

import cbc.motors.Motor;
import cbc.movement.DriveTrain;
import cbc.movement.DriveTrainPosition;
import cbc.movement.plugins.MovementPlugin;
import cbc.movement.plugins.motor.MotorMovementPlugin;
import cbc.movement.plugins.motor.Wheel;
import utils.Constants;

//RIGHT number goes up as arm goes up
public abstract class LegoRobot extends Robot {
	
	public static final int WHEEL_RIGHT_PORT = 0;
	public static final int WHEEL_LEFT_PORT = 3;
	public static final double WHEEL_DIAMETER = 5.5;
	public static final double WHEEL_CIRCUMFERENCE = Math.PI*WHEEL_DIAMETER;
	public static final double WHEEL_DISTANCE = 14;
	public static final int SERVO_RIGHT_PORT = 0;
	public static final int SERVO_LEFT_PORT = 3;
	public static final int ARM_MOTOR_PORT = 1;
	public static final int ET_PORT = 0;
	public static final int ARM_TOUCH_PORT = 8;
	public static final int ET_STOP_VALUE = 450;
	
	public static Wheel wheelR =  new Wheel(WHEEL_RIGHT_PORT, WHEEL_CIRCUMFERENCE);
	public static Wheel wheelL = new Wheel(WHEEL_LEFT_PORT, WHEEL_CIRCUMFERENCE);
	

	LegoRobot() {																																								
		super(new DriveTrain(new MotorMovementPlugin(wheelR, wheelL, WHEEL_DISTANCE)));
		// TODO Auto-generated constructor stub
	}
}