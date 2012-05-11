package robot;


import cbc.motors.Motor;
import cbc.motors.Servo;
import cbc.movement.plugins.MovementPlugin;
import cbc.movement.plugins.motor.Wheel;
import cbc.sensors.analog.Analog;
import cbc.sensors.digital.Touch;
import robot.extentions.ArmRobot;
import robot.extentions.ClawRobot;
import robot.extentions.GrabRobot;
import utils.KelpConstants;

public class KelpRobot extends LegoRobot implements ArmRobot, ClawRobot, GrabRobot, KelpConstants {
	


	public KelpRobot() {
		super(new Wheel(WHEEL_LEFT_PORT, WHEEL_CIRCUMFERENCE), new Wheel(WHEEL_RIGHT_PORT, WHEEL_CIRCUMFERENCE), WHEEL_DISTANCE);
		// TODO Auto-generated constructor stub
	}

	public KelpRobot(MovementPlugin plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}


	Servo servoR = new Servo(SERVO_RIGHT_PORT);
	Servo servoL = new Servo(SERVO_LEFT_PORT);
	Motor armM = new Motor(ARM_MOTOR_PORT);
	Touch armTouch = new Touch(ARM_TOUCH_PORT);
	Analog ETSensor = new Analog(ET_PORT);

	public static final class Values{
		static final int[] armLevels = {0,1};

		public Values() {
			super();
			// TODO Auto-generated constructor stub
		}
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
				while(armM.getPosition() < 2500){}
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
		getClaw().close();
		getArm().goToPos(2);
		while(ETSensor.getValueHigh() < 410){
			getDriveTrain().moveAtCmps(5);
		}
		getDriveTrain().kill();
		getDriveTrain().moveCm(-10, 4);
		getClaw().halfOpen();
		getArm().goToPos(0);
		getDriveTrain().moveCm(6.75, 4);
	//	if(getDriveTrain().)
		armM.moveAtVelocity(-500);
		getDriveTrain().moveCm(5.5, 3);
		while(armM.getPosition() > 0){}
		armM.clearPositionCounter();
		armM.off();
		getDriveTrain().moveAtCmps(-3); 
		getArm().goToPos(2); 
		getDriveTrain().kill();
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

