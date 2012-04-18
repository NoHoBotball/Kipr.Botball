package robot;

import cbccore.create.Create;
import cbccore.create.CreateConnectException;
import cbccore.movement.DriveTrain;
import cbccore.movement.efficiency.IEfficiencyCalibrator;
import cbccore.movement.plugins.create.CreateMovementPlugin;

public abstract class CreateRobot extends Robot {

	
	CreateRobot() throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create())));
	}
	CreateRobot(double leftEfficiency, double rightEfficiency) throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create(),leftEfficiency, rightEfficiency)));
	}
	CreateRobot(IEfficiencyCalibrator leftEfficiency, IEfficiencyCalibrator rightEfficiency) throws CreateConnectException {
		this(leftEfficiency, rightEfficiency, false);
	}
	
	CreateRobot(IEfficiencyCalibrator leftEfficiency, IEfficiencyCalibrator rightEfficiency, boolean fullMode) throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create(), leftEfficiency, rightEfficiency, fullMode)));
	}

}
