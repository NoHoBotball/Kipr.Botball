package robot;

import cbccore.motors.Motor;
import cbccore.movement.DriveTrain;
import cbccore.movement.DriveTrainPosition;
import cbccore.movement.plugins.MovementPlugin;
import cbccore.movement.plugins.motor.MotorMovementPlugin;
import cbccore.movement.plugins.motor.Wheel;
import utils.Constants;


public abstract class LegoRobot extends Robot {
	public static Wheel wheelR =  new Wheel(Constants.WHEEL_RIGHT_PORT, Constants.WHEEL_CIRCUMFERENCE);
	public static Wheel wheelL = new Wheel(Constants.WHEEL_LEFT_PORT, Constants.WHEEL_CIRCUMFERENCE);


	LegoRobot() {																																								
		super(new DriveTrain(new MotorMovementPlugin(wheelR, wheelL, Constants.WHEEL_DISTANCE)));
		// TODO Auto-generated constructor stub
	}



}