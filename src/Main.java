import java.util.List;

import cbc.create.CreateConnectException;
import cbc.sensors.buttons.AButton;
import cbc.sensors.buttons.BButton;
import cbc.sensors.buttons.BlackButton;

import regionals2012.TaskChain;
import regionals2012.TaskRunner;
import robot.BlockRobot;
import robot.KelpRobot;

import utils.vision.Block;
import utils.pathfinding.Task;
import utils.pathfinding.TaskException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		try {
			List<Task> kelpChain = TaskChain.moveToKelpChain(1);
			TaskRunner kelpTask = new TaskRunner(new KelpRobot(), kelpChain);
			kelpTask.run();
		} catch (TaskException e){
			e.printStackTrace();
		} 
		
	}

}
