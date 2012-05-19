package robot;

import cbc.movement.DriveTrain;
import cbc.movement.plugins.MovementPlugin;
import cbc.movement.plugins.motor.MotorMovementPlugin;
import cbc.movement.plugins.motor.Wheel;

//RIGHT number goes up as arm goes up
public abstract class LegoRobot extends Robot {
	public LegoRobot(Wheel lWheel, Wheel rWheel, double trainWidth) {
		super(new DriveTrain(new MotorMovementPlugin(rWheel, lWheel, trainWidth)));
			/*public void moveCm(double distance, double speed) {
				if(distance > 5){
					super.moveCm(distance-5, speed);
					super.moveCm(5, speed/2);
				} else if (distance < -5) {
					super.moveCm(distance+5, speed);
					super.moveCm(-5, speed/2);
				} else
					super.moveCm(distance, speed);
				}*/
			}
		//);
	

	public LegoRobot(MovementPlugin plugin) {
		super(new DriveTrain(plugin));
	}
}