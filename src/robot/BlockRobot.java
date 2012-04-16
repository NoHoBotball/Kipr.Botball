package robot;

import cbccore.create.CreateConnectException;
import robot.extentions.ArmRobot;
import robot.extentions.ClawRobot;
import robot.extentions.GrabRobot;

public class BlockRobot extends CreateRobot implements ArmRobot, ClawRobot, GrabRobot {

	public static final class Values{
		static final int[] armLevels = {0,1,2};
	}
	
	
	public BlockRobot() throws CreateConnectException {
		super();
	}
	
	Arm arm = new Arm(Values.armLevels){
		public void goToPos(int pos) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// TODO: Implement arm motion
		}
	};
	public Arm getArm() {
		return arm;
	}
	
	
	Claw claw = new Claw(){
		public void open() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// TODO: Implement claw motion
		}
		public void close() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// TODO: Implement claw motion
		}
	};
	public Claw getClaw() {
		return claw;
	}
	
	public void grab() {
		
	}

}
