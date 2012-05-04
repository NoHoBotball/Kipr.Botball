
import cbc.sensors.buttons.LeftButton;
import cbc.sensors.buttons.AButton;
import cbc.sensors.buttons.BButton;
import cbc.sensors.buttons.BlackButton;
import cbc.sensors.buttons.UpButton;
import robot.KelpRobot;

public class KelpDrive {
	public static void main (String[] args){
		KelpRobot kelpRobot = new KelpRobot();
		BlackButton blackButton = new BlackButton();
		UpButton upButton = new UpButton();
		AButton aButton = new AButton();
		BButton bButton = new BButton();
		LeftButton leftButton = new LeftButton();

		while(leftButton.getValue() == false){
			if (upButton.getValue() == true)
				kelpRobot.getDriveTrain().moveCm(100, 800);
			if (aButton.getValue() == true)
				kelpRobot.getDriveTrain().rotateDegrees(90, 15);
			if (bButton.getValue() == true){
				kelpRobot.getDriveTrain().rotateDegrees(-90, 15);
			}
		}
	}
}
