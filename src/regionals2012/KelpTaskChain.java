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
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();


		//TODO: Fill out and plan opening moves.
		if (firstOrSecond == 1){
			System.out.println("first");
			tasks.add(TurnTask.turnCW());
			tasks.add(new DriveTask(Constants.inchesToCentimeters(27), Constants.STANDARD_KELP_SPEED)); //Exit SB
			tasks.add(new TurnTask (90, Constants.STANDARD_KELP_SPEED)); //turn CCW
			tasks.add(new DriveTask(Constants.inchesToCentimeters(19), Constants.STANDARD_KELP_SPEED)); //drive forward 
			tasks.add(new TurnTask (90, Constants.STANDARD_KELP_SPEED)); //turn CCW
			tasks.add(new ETDriveTask(Constants.STANDARD_KELP_SPEED, 450));
			tasks.add(new DriveTask(Constants.inchesToCentimeters(18.25), -Constants.STANDARD_KELP_SPEED));
			tasks.add(new TurnTask (-90, Constants.STANDARD_KELP_SPEED));
		} else if (firstOrSecond == 2){
			tasks.add(new DriveTask(-100, Constants.STANDARD_KELP_SPEED));
			tasks.add(new TurnTask (-90, Constants.STANDARD_KELP_SPEED));
			tasks.add(new ETDriveTask(Constants.STANDARD_KELP_SPEED, 450));
			tasks.add(new DriveTask(-1232, Constants.STANDARD_KELP_SPEED));
			tasks.add(new TurnTask (90, Constants.STANDARD_KELP_SPEED));
		} else {
			System.out.println("ERROR: firstOrSecond must be either 1 or 2.");
		}




		// Robot is in starting position for block grabbing 
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



		tasks.add(new TurnTask (180, Constants.STANDARD_KELP_SPEED));
		tasks.add(new ETDriveTask(Constants.STANDARD_KELP_SPEED, 450));
		tasks.add(new DriveTask(-100, Constants.STANDARD_KELP_SPEED));
		tasks.add(new TurnTask (90, Constants.STANDARD_KELP_SPEED));
		tasks.add(ReleaseTask.getTask());




		return tasks;

	}

}
