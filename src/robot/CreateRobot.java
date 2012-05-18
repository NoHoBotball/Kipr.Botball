package robot;

import cbc.create.Create;
import cbc.create.CreateConnectException;
import cbc.movement.DriveTrain;
import cbc.movement.efficiency.IEfficiencyCalibrator;
import cbc.movement.plugins.create.CreateMovementPlugin;

public abstract class CreateRobot extends Robot {

	CreateRobot() throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create())) {
			@Override
			protected void moveWheelCm(double leftCm, double rightCm, //positions
	                    double leftCmps, double rightCmps) { 
					leftCm = -leftCm;
					rightCm = -rightCm;
					super.moveWheelCm(leftCm, rightCm, leftCmps, rightCmps);
				}
			}
		);
	}
	CreateRobot(double leftEfficiency, double rightEfficiency) throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create(),leftEfficiency, rightEfficiency)){
			@Override
			protected void moveWheelCm(double leftCm, double rightCm, //positions
	                    double leftCmps, double rightCmps) { 
					leftCm = -leftCm;
					rightCm = -rightCm;
				}
			}
		);
	}
	CreateRobot(IEfficiencyCalibrator leftEfficiency, IEfficiencyCalibrator rightEfficiency) throws CreateConnectException {
		this(leftEfficiency, rightEfficiency, false);
	}
	
	CreateRobot(IEfficiencyCalibrator leftEfficiency, IEfficiencyCalibrator rightEfficiency, boolean fullMode) throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create(), leftEfficiency, rightEfficiency, fullMode)){
			@Override
			protected void moveWheelCm(double leftCm, double rightCm, //positions
	                    double leftCmps, double rightCmps) { 
					leftCm = -leftCm;
					rightCm = -rightCm;
				}
			}
		);
	}
	
	
}
