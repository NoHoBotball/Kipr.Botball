
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
import robot.extentions.GrabRobot;
import utils.Conversions;
import utils.KelpConstants;
import utils.tasks.GrabTask;

public class KelpDrive implements KelpConstants {
	public static void main (String[] args){

		Servo servoR = new Servo(SERVO_RIGHT_PORT);
		Servo servoL = new Servo(SERVO_LEFT_PORT);
		Motor armM = new Motor(ARM_MOTOR_PORT);
		Analog ETSensor = new Analog(ET_PORT);

		KelpRobot kelpRobot = new KelpRobot();
		BlackButton blackButton = new BlackButton();
		UpButton upButton = new UpButton();
		DownButton downButton = new DownButton();
		AButton aButton = new AButton();
		BButton bButton = new BButton();
		LeftButton leftButton = new LeftButton();
		RightButton rightButton = new RightButton();

		while(servoL.getPosition() > 48){
			servoR.setPosition(servoR.getPosition() + 30);
			servoL.setPosition(servoL.getPosition() - 30);
			System.out.println("ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
		}
		servoR.setPosition(2000);
		servoL.setPosition(48);
		ETSensor.setFloating(true);  

		while(blackButton.getValue() == false){   
			if (rightButton.getValue() == true){
				double x = Math.random() * 20;
				System.out.println("Should move " + x + " inches.");
				kelpRobot.getDriveTrain().moveCm(Conversions.inToCm(x)/1.13, 10);
				//kelpRobot.getDriveTrain().moveCm(KelpRobot.calibratedValue(KelpConstants.calibrator,Conversions.inToCm(x)), 10);
			} else if (aButton.getValue() == true){   
				kelpRobot.getClaw().open();         
			} else if (bButton.getValue() == true){  
				kelpRobot.getClaw().close();  
			} else if (leftButton.getValue() == true){ 
				kelpRobot.grab();      
			} else if (downButton.getValue() == true){ 
				double distanceToTravel =  (KelpConstants.ROBOT_TURN_CIRCUMFERENCE*(90/360))/1.3;
				kelpRobot.getDriveTrain().rotateDegrees((distanceToTravel/KelpConstants.ROBOT_TURN_CIRCUMFERENCE)*360,10 );
				//kelpRobot.getDriveTrain().rotateDegrees(90, degreesPerSecond)
			} else if (upButton.getValue() == true){
				kelpRobot.getDriveTrain().moveCm(Conversions.inToCm(24), kelpRobot.getDriveTrain().getMaxCmps());
			} else {     
				Motor.allOff();
			} 
			//System.out.println("LOOPBACK! ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
		}
	} 
}
