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


	public static List<Task> moveToKelpChain(int firstOrSecond) throws TaskException{
		List<Task> tasks = new ArrayList<Task>();

		if (firstOrSecond == 1){
			tasks.add(TurnTask.turnCCW(15));
			tasks.add(new DriveTask(27, STANDARD_KELP_SPEED)); //27
			tasks.add(TurnTask.turnCCW());
			tasks.add(new DriveTask(24, STANDARD_KELP_SPEED)); //drive forward 
			tasks.add(TurnTask.turnCCW());
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 950));
			tasks.add(new DriveTask(0,0));
			tasks.add(new ETDriveTask(-STANDARD_KELP_SPEED, 255));
			tasks.add(new DriveTask(-20, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCW());
		} else if (firstOrSecond == 2){
			tasks.add(new DriveTask(-5, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
			tasks.add(new TurnTask(5,-STANDARD_KELP_SPEED));
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 950));
			tasks.add(new DriveTask(0,0));
			tasks.add(new ETDriveTask(-STANDARD_KELP_SPEED, 255));
			tasks.add(new DriveTask(-12, STANDARD_KELP_SPEED));
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


	public static List<Task> returnKelpChain(int firstOrSecond) throws TaskException{
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();	

		tasks.add(new DriveTask(-8, STANDARD_KELP_SPEED));
		if(firstOrSecond == 1){
			tasks.add(TurnTask.turnAround());
			tasks.add(new TurnTask(30,-STANDARD_KELP_SPEED));
		}
		if(firstOrSecond == 2){
			tasks.add(TurnTask.turnCCW());
			tasks.add(new TurnTask(5,-STANDARD_KELP_SPEED));
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 960));
			tasks.add(new DriveTask(-4, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
		}
		tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 450));
		tasks.add(new TurnTask(20,STANDARD_KELP_SPEED));
		tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 960));
		tasks.add(new DriveTask(7,4));

		tasks.add(ReleaseTask.getTask());

		return tasks;
	}

}
