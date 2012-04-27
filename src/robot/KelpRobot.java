package robot;


import cbccore.motors.Motor;
import cbccore.motors.Servo;
import cbccore.sensors.analog.Analog;
import cbccore.sensors.digital.Touch;
import cbccore.sensors.analog.ETSensor;
import robot.extentions.ArmRobot;
import robot.extentions.ClawRobot;
import robot.extentions.GrabRobot;
import utils.Constants;

public class KelpRobot extends LegoRobot implements ArmRobot, ClawRobot, GrabRobot {
	


	Servo servoR = new Servo(SERVO_RIGHT_PORT);
	Servo servoL = new Servo(SERVO_LEFT_PORT);
	Motor armM = new Motor(ARM_MOTOR_PORT);
	Touch armTouch = new Touch(ARM_TOUCH_PORT);
	Analog ETSensor = new Analog(ET_PORT);

	public static final class Values{
		static final int[] armLevels = {0,1};
	}


	public KelpRobot() {
		super();
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
		claw.open();
		arm.goToPos(0);
		while(ETSensor.getValueHigh() > 450){
			super.getDriveTrain().moveAtCmps(ETSensor.getValueHigh()-400);
		}
		super.getDriveTrain().kill();
		claw.close();
		
		
	}

}
