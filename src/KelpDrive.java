
import cbc.motors.Motor;
import cbc.motors.Servo;
import cbc.sensors.analog.Analog;
import cbc.sensors.analog.ETSensor;
import cbc.sensors.buttons.DownButton;
import cbc.sensors.buttons.LeftButton;
import cbc.sensors.buttons.AButton;
import cbc.sensors.buttons.BButton;
import cbc.sensors.buttons.BlackButton;
import cbc.sensors.buttons.RightButton;
import cbc.sensors.buttons.UpButton;
import cbc.sensors.digital.Touch;
import robot.LegoRobot;
import robot.KelpRobot;
import utils.Constants;

public class KelpDrive {
	public static void main (String[] args){

		Servo servoR = new Servo(LegoRobot.SERVO_RIGHT_PORT);
		Servo servoL = new Servo(LegoRobot.SERVO_LEFT_PORT);
		Motor armM = new Motor(LegoRobot.ARM_MOTOR_PORT);
		Analog ETSensor = new Analog(LegoRobot.ET_PORT);

		KelpRobot kelpRobot = new KelpRobot();
		BlackButton blackButton = new BlackButton();
		UpButton upButton = new UpButton();
		DownButton downButton = new DownButton();
		AButton aButton = new AButton();
		BButton bButton = new BButton();
		LeftButton leftButton = new LeftButton();
		RightButton rightButton = new RightButton();

		servoR.setPosition(1024);
		servoL.setPosition(1024);
		ETSensor.setFloating(true);
		
		while(blackButton.getValue() == false){
			if (rightButton.getValue() == true){
				kelpRobot.getDriveTrain().moveCm(Constants.inchesToCentimeters(24), kelpRobot.getDriveTrain().getMaxCmps());
				//max is 1210 ticks per second
			} else if (aButton.getValue() == true){  
				/*kelpRobot.getDriveTrain().moveAtCmps(kelpRobot.getDriveTrain().getMaxCmps()/2);
				try { Thread.sleep(4000); } catch (Exception e) {}
				kelpRobot.getDriveTrain().kill();*/
				kelpRobot.getClaw().open();
			} else if (bButton.getValue() == true){
				kelpRobot.getClaw().close();  
				//kelpRobot.getDriveTrain().rotateDegrees(-90, kelpRobot.getDriveTrain().getMaxCmps()/2);
			} else if (leftButton.getValue() == true){
				kelpRobot.getClaw().halfOpen();
				kelpRobot.getArm().goToPos(0);
				while(ETSensor.getValueHigh() < 400){
					kelpRobot.getDriveTrain().moveAtCmps(5);
				}
				kelpRobot.getDriveTrain().kill();
				kelpRobot.getClaw().close();
				kelpRobot.getDriveTrain().moveAtCmps(-2);
				kelpRobot.getArm().goToPos(2);  
				kelpRobot.getDriveTrain().kill();
			} else if (downButton.getValue() == true){ 
				kelpRobot.getArm().goToPos(0);
				//try { Thread.sleep(4000); } catch (Exception e) {}
				System.out.println("END OF DOWN! ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition()); 
			} else if (upButton.getValue() == true){
				kelpRobot.getArm().goToPos(2);
				//try { Thread.sleep(4000); } catch (Exception e) {}
				System.out.println("END OF UP! ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
			}
			System.out.println("LOOPBACK! ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
		}
	} 
}
