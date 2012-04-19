package robot;


import cbccore.motors.Motor;
import cbccore.motors.Servo;
import cbccore.sensors.digital.Touch;
import robot.extentions.ArmRobot;
import robot.extentions.ClawRobot;
import robot.extentions.GrabRobot;

public class KelpRobot extends LegoRobot implements ArmRobot, ClawRobot, GrabRobot {

	Servo servoR = new Servo(3);
	Servo servoL = new Servo(0);
	Motor armM = new Motor(0);
	Touch armTouch = new Touch(0);

	public static final class Values{
		static final int[] armLevels = {0,1};
	}


	public KelpRobot(int serR, int serL, int armPort) {
		super(0, 3, 3, 5); 
	}

	Arm arm = new Arm(Values.armLevels){
		public void goToPos(int pos) {
			try {
				switch(pos) {
				case 0: 
					while(servoR.getPosition() < servoR.getMaxPosition() - 50){
						servoR.setPosition(servoR.getPosition() + 30);
						servoL.setPosition(servoL.getPosition() - 30);
						Thread.sleep(100);
					}
				case 1: 
					while(servoR.getPosition() < servoR.getMinPosition() - 50){
						servoR.setPosition(servoR.getPosition() - 30);
						servoL.setPosition(servoL.getPosition() + 30);
						Thread.sleep(100);
					}
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	};

	public Arm getArm() {
		return arm;
	}


	Claw claw = new Claw(){
		public void open() {
			try {
				armM.moveAtVelocity(800);
				while(armM.getPosition() < 4000){}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Lower kelp arm
		}
		public void close() {
			try {
				armM.moveAtVelocity(-800);
				while(armTouch.getValue() == false){}
				armM.off();
				armM.clearPositionCounter();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Raise kelp arm
		}
	};

	public Claw getClaw() {
		return claw;
	}

	public void grab() { //should move forward while raising claw while spinning arm

	}

}
