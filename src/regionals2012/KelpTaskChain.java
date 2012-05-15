package regionals2012;

import java.util.ArrayList;
import java.util.List;

import robot.KelpRobot;

import utils.Constants;
import utils.Constants.Location;
import utils.Constants.Direction;
import utils.pathfinding.DriveTask;
import utils.pathfinding.ETDriveTask;
import utils.pathfinding.GrabTask;
import utils.pathfinding.ReleaseTask;
import utils.pathfinding.Task;
import utils.pathfinding.TaskException;
import utils.pathfinding.TurnTask;
import utils.vision.Block;

/**
 * Put all tasks chains to be generated throughout the duration
 * of the 2012 Botball Greater LA regional competition in this
 * class as static methods.
 *
 */
public class KelpTaskChain {



	public static List<Task> moveToKelpChain(int firstOrSecond) throws TaskException{
		List<Task> tasks = new ArrayList<Task>();

		if (firstOrSecond == 1){
			tasks.add(TurnTask.turnCCW());
			tasks.add(new DriveTask(27, Constants.STANDARD_KELP_SPEED)); //Exit SB
			tasks.add(TurnTask.turnCCW());
			tasks.add(new DriveTask(19, Constants.STANDARD_KELP_SPEED)); //drive forward 
			tasks.add(TurnTask.turnCCW());
			tasks.add(new ETDriveTask(Constants.STANDARD_KELP_SPEED, 950));
			tasks.add(new DriveTask(-18.25, Constants.STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCW());
		} else if (firstOrSecond == 2){
			tasks.add(new DriveTask(-100, Constants.STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
			tasks.add(new ETDriveTask(Constants.STANDARD_KELP_SPEED, 410));
			tasks.add(new DriveTask(-1232, Constants.STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
		} else 
			System.out.println("ERROR: firstOrSecond must be either 1 or 2.");

		return tasks;
	}

	public static List<Task> getKelpChain() throws TaskException {
		List<Task> tasks = new ArrayList<Task>();

		tasks.add(GrabTask.getTask());
		
		return tasks;
	}


	public static List<Task> returnKelpChain() throws TaskException{
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();
		
		tasks.add(TurnTask.turnCCW());
		tasks.add(new ETDriveTask(Constants.STANDARD_KELP_SPEED, 410));
		tasks.add(new DriveTask(-100, Constants.STANDARD_KELP_SPEED));
		tasks.add(TurnTask.turnCCW());
		tasks.add(ReleaseTask.getTask());

		return tasks;
	}

}
