package regionals2012;

import java.util.ArrayList;
import java.util.List;

import utils.tasks.DriveTask;
import utils.tasks.ETDriveTask;
import utils.tasks.GrabTask;
import utils.tasks.ReleaseTask;
import utils.tasks.Task;
import utils.tasks.TaskException;
import utils.tasks.TurnTask;
import utils.Conversions;
import utils.KelpConstants;
import utils.pathfinding.Location;
import utils.pathfinding.Direction;


/**
 * Put all tasks chains to be generated throughout the duration
 * of the 2012 Botball Greater LA regional competition in this
 * class as static methods.
 *
 */

public class KelpTaskChain implements KelpConstants {
	// Store robot state as the chain builds
	static Location location = Location.GAME_START; // Robot is in starting position for block grabbing
	static Direction offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
	static Direction heading = Direction.SOUTH; // Robot is facing down



	public static List<Task> moveToKelpChain(int firstOrSecond) throws TaskException{
		List<Task> tasks = new ArrayList<Task>();

		if (firstOrSecond == 1){
			tasks.add(TurnTask.turnCCW());
			tasks.add(new DriveTask(27, STANDARD_KELP_SPEED)); //Exit SB
			tasks.add(TurnTask.turnCCW());
			tasks.add(new DriveTask(19, STANDARD_KELP_SPEED)); //drive forward 
			tasks.add(TurnTask.turnCCW());
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 950));
			tasks.add(new DriveTask(-18.25, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCW());
		} else if (firstOrSecond == 2){
			tasks.add(new DriveTask(-100, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 410));
			tasks.add(new DriveTask(-1232, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
		} else {
			System.out.println("ERROR: firstOrSecond must be either 1 or 2.");
		}
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
		tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 410));
		tasks.add(new DriveTask(-100, STANDARD_KELP_SPEED));
		tasks.add(TurnTask.turnCCW());

		tasks.add(ReleaseTask.getTask());

		return tasks;
	}

}
