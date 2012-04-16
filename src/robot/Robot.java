package robot;

import cbccore.movement.DriveTrain;

public abstract class Robot {
	DriveTrain driveTrain;
	Robot(DriveTrain dt){
		this.driveTrain = dt;
	}
	
	public DriveTrain getDriveTrain() {
		return driveTrain;
	}
}
