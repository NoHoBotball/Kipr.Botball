package robot;


import cbc.motors.Motor;
import cbc.motors.Servo;
import cbc.sensors.analog.Analog;
import cbc.sensors.digital.Touch;
import robot.extentions.ArmRobot;
import robot.extentions.ClawRobot;
import robot.extentions.GrabRobot;

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
			if(pos == 0){
				while(servoR.getPosition() > 348){
					servoR.setPosition(servoR.getPosition() - 30);
					servoL.setPosition(servoL.getPosition() + 30);
					System.out.println("ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
				}

				servoR.setPosition(348);
				servoL.setPosition(1700);
			}
			else if (pos == 1){
				while(servoR.getPosition() > 1024 + 30){
					servoR.setPosition(servoR.getPosition() - 30);
					servoL.setPosition(servoL.getPosition() + 30);
					System.out.println("ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
				}
				while(servoL.getPosition() < 1024 - 30){
					servoR.setPosition(servoR.getPosition() + 30);
					servoL.setPosition(servoL.getPosition() - 30);
					System.out.println("ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
				}
				servoR.setPosition(1024);
				servoL.setPosition(1024);
			}
			else if (pos == 2){
				while(servoL.getPosition() > 48){
					servoR.setPosition(servoR.getPosition() + 30);
					servoL.setPosition(servoL.getPosition() - 30);
					System.out.println("ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
				}
				servoR.setPosition(2000);
				servoL.setPosition(48);
			}
			/*case 2: //SET TO MIDDLE
					servoR.setPosition(1024);
					servoL.setPosition(1024);
			}*/
		}
	};

	public Arm getArm() {
		return arm;
	}


	Claw claw = new Claw(){
		public void open() {
			try {
				armM.moveAtVelocity(500);
				while(armM.getPosition() < 3000){}
				armM.off();
				Thread.sleep(100);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Lower kelp arm
		}
		public void close() {
			try {
				armM.moveAtVelocity(-500);
				/*while(armTouch.getValue() == false){}
				armM.off();*/
				while(armM.getPosition() > 0){}
				armM.clearPositionCounter();
				armM.off();
				Thread.sleep(100);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Raise kelp arm
		}
		public void halfOpen() {
			try {
				/*while(armTouch.getValue() == false){}
				armM.off();*/
				if(armM.getPosition() > 1450){
					armM.moveAtVelocity(-500);
				}
				if(armM.getPosition() < 1350){
					armM.moveAtVelocity(500);
				}
				while(armM.getPosition() > 1450 || armM.getPosition() < 1350 ){}
				armM.off();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
		super.getDriveTrain().moveAtCmps(-50);
		arm.goToPos(1);
		super.getDriveTrain().kill();
	}

	public void release(){
		while(ETSensor.getValueHigh() > 450){
			super.getDriveTrain().moveAtCmps(ETSensor.getValueHigh()-400);
		}
		super.getDriveTrain().kill();
		arm.goToPos(1);
		claw.open();
		super.getDriveTrain().moveAtCmps(-100);
		claw.halfOpen();
		arm.goToPos(0);
		super.getDriveTrain().kill();
	}
}

