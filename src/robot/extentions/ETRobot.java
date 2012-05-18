package robot.extentions;

import cbc.movement.DriveTrain;
import cbc.sensors.analog.Analog;

public interface ETRobot {
	public DriveTrain getDriveTrain();
	public Analog getETSensor();
	public int getETSensorValue();
}
