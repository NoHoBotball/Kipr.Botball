package robot;

import cbccore.motors.Motor;
import cbccore.movement.DriveTrain;
import cbccore.movement.DriveTrainPosition;
import cbccore.movement.plugins.MovementPlugin;
import cbccore.movement.plugins.motor.MotorMovementPlugin;
import cbccore.movement.plugins.motor.Wheel;


public abstract class LegoRobot extends Robot {

	LegoRobot(int mPortR, int mPortL, double wheelCircumference, double wheelDistance) {																																								
		super(new DriveTrain(new MotorMovementPlugin(new Wheel(mPortR, wheelCircumference), 
				new Wheel(mPortL, wheelCircumference), wheelDistance)));
		// TODO Auto-generated constructor stub
	}




}