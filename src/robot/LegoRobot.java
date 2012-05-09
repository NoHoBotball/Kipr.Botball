package robot;

import cbc.movement.DriveTrain;
import cbc.movement.plugins.MovementPlugin;
import cbc.movement.plugins.motor.MotorMovementPlugin;
import cbc.movement.plugins.motor.Wheel;

//RIGHT number goes up as arm goes up
public abstract class LegoRobot extends Robot {
	public LegoRobot(Wheel lWheel, Wheel rWheel, double trainWidth) {
		super(new DriveTrain(new MotorMovementPlugin(lWheel, rWheel, trainWidth)));
	}
	
	public LegoRobot(MovementPlugin plugin) {
		super(new DriveTrain(plugin));
	}
}