package robot;

import cbc.movement.DriveTrain;

public abstract class Robot {
	DriveTrain driveTrain;
	Robot(DriveTrain dt){
		this.driveTrain = dt;
	}
	
	public DriveTrain getDriveTrain() {
		return driveTrain;
	}
}
