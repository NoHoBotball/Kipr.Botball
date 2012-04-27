
import cbccore.sensors.buttons.AButton;
import cbccore.sensors.buttons.BButton;
import cbccore.sensors.buttons.BlackButton;
import cbccore.sensors.buttons.UpButton;
import robot.KelpRobot;

public class Main {
	public static void main (String[] args){
		KelpRobot kelpRobot = new KelpRobot();
		BlackButton blackButton = new BlackButton();
		UpButton upButton = new UpButton();
		AButton aButton = new AButton();
		BButton bButton = new BButton();

		while(blackButton.getValue() == false){
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
