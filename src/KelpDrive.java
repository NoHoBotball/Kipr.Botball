
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
import utils.KelpConstants;

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
	 	
		servoR.setPosition(1024); 
		servoL.setPosition(1024);     
		ETSensor.setFloating(true); 
 
		while(blackButton.getValue() == false){
			if (rightButton.getValue() == true){
				kelpRobot.getDriveTrain().moveCm(Constants.inchesToCentimeters(24), kelpRobot.getDriveTrain().getMaxCmps());
				//max is 1210 ticks per second
			} else if (aButton.getValue() == true){  
				kelpRobot.getClaw().open();
			} else if (bButton.getValue() == true){
				kelpRobot.getClaw().close();
			} else if (leftButton.getValue() == true){
				kelpRobot.grab(); 
			} else if (downButton.getValue() == true){ 
				kelpRobot.getDriveTrain().directDrive(kelpRobot.getDriveTrain().getMaxCmps(), kelpRobot.getDriveTrain().getMaxCmps());//.845));	
			} else if (upButton.getValue() == true){
				kelpRobot.getDriveTrain().rotateDegrees(90, 15);
			} else {  
				//kelpRobot.
				//Servo.allOff();
				Motor.allOff();
			}
			System.out.println("LOOPBACK! ServoR: " + servoR.getPosition() + "ServoL: " + servoL.getPosition());
		}
	} 
}
