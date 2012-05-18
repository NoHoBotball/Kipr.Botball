
package robot;

import cbc.create.CreateConnectException;
import robot.extentions.AdjustBlockRobot;
import robot.extentions.ArmRobot;
import robot.extentions.ClawRobot;
import robot.extentions.GetBlockRobot;
import robot.extentions.GrabRobot;
import robot.extentions.StackBlockRobot;

public class BlockRobot extends CreateRobot implements ArmRobot, ClawRobot, GrabRobot, GetBlockRobot, AdjustBlockRobot, StackBlockRobot {

	public static final class Values{
		static final int[] armLevels = {0,1,2};
	}
	
	
	public BlockRobot() throws CreateConnectException {
		super();
	}
	
	private Arm arm = new Arm(Values.armLevels){
		@Override
		public void goToPos(int pos) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// TODO: Implement arm motion
		}
	};
	
	@Override
	public Arm getArm() {
		return arm;
	}
	
	
	private Claw claw = new Claw(){
		@Override
		public void open() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// TODO: Implement claw motion
		}
		@Override
		public void close() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// TODO: Implement claw motion
		}
		@Override
		public void halfOpen() {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public Claw getClaw() {
		return claw;
	}
	
	@Override
	public void grab() {
		// TODO: implement grab functionality
	}

	@Override
	public void release() {
		// TODO: implement release functionality		
	}

	@Override
	public void adjustBlock() {
		// TODO: implement adjust functionality		
	}

	@Override
	public void stack() {
		// TODO: implement stack funcitionality
		
	}

}
