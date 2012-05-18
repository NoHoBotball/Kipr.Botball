
package robot;

import cbc.create.CreateConnectException;
import cbc.motors.Servo;
import robot.extentions.AdjustBlockRobot;
import robot.extentions.ArmRobot;
import robot.extentions.ClawRobot;
import robot.extentions.GetBlockRobot;
import robot.extentions.GrabRobot;
import utils.BlockConstants;
import utils.Conversions;

public class BlockRobot extends CreateRobot implements ArmRobot, ClawRobot, GrabRobot, GetBlockRobot, AdjustBlockRobot {
	
	private boolean grabbedFirstBlock = false;
	
	public static final class Values{
		static final int[] armLevels = {0,1,2};
		static final int[] clawLevels = {0,575,1150};
	}
	
	
	public BlockRobot() throws CreateConnectException {
		super();
	}
	
	private Arm arm = new Arm(Values.armLevels){
		private Servo s1 = new Servo(BlockConstants.ARM_PORT_1), s2 = new Servo(BlockConstants.ARM_PORT_2);
		@Override
		public void goToPos(int pos) {
			s1.setPosition(pos, false);
			s2.setPosition(pos, true);
		}
	};
	
	@Override
	public Arm getArm() {
		return arm;
	}
	
	
	private Claw claw = new Claw(){
		private Servo s = new Servo(BlockConstants.CLAW_CONTROL_PORT);
		@Override
		public void open() {
			s.setPosition(Values.clawLevels[2], true);
		}
		@Override
		public void close() {
			s.setPosition(Values.clawLevels[0], true);
		}
		@Override
		public void halfOpen() {
			s.setPosition(Values.clawLevels[1], true);
		}
	};
	
	@Override
	public Claw getClaw() {
		return claw;
	}
	
	@Override
	public void grab() {
		if (!grabbedFirstBlock) {
			arm.lower();
			driveTrain.moveCm(Conversions.inToCm(5), BlockConstants.APPROACH_SPEED);
			claw.close();
			grabbedFirstBlock = true;
		} else {
			driveTrain.moveCm(Conversions.inToCm(BlockConstants.CLAW_FREE - BlockConstants.DROP_DIST), BlockConstants.APPROACH_SPEED);
			claw.open();
			driveTrain.moveCm(Conversions.inToCm(-(BlockConstants.ARM_BUFFER + 1)), BlockConstants.APPROACH_SPEED);
			arm.lower();
			driveTrain.moveCm(Conversions.inToCm(BlockConstants.ARM_BUFFER), BlockConstants.APPROACH_SPEED);
			claw.close();
		}
	}

	@Override
	public void release() {
		// TODO Implement release code
	}

	@Override
	public void adjustBlock() {
		// TODO Auto-generated method stub
	}

}
