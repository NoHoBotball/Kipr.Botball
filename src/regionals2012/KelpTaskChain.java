package regionals2012;

import java.util.ArrayList;
import java.util.List;

import robot.KelpRobot;

import utils.tasks.DriveTask;
import utils.tasks.GrabTask;
import utils.tasks.ReleaseTask;
import utils.tasks.Task;
import utils.tasks.TaskException;
import utils.tasks.TurnTask;
import utils.Conversions;
import utils.KelpConstants;
import utils.pathfinding.Location;
import utils.pathfinding.Direction;
import utils.vision.Block;

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
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();

		if(location != Location.GAME_START // Robot is in starting position for block grabbing
				|| offset != Direction.CENTER // Robot is on exact spot; different values define where the bot is relative to a block's center position
				//   || heading != Direction.WEST // Robot is facing down
				) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");


		//TODO: Fill out and plan opening moves.
		if (firstOrSecond == 1){
			tasks.add(TurnTask.turn(90));
			tasks.add(new DriveTask(Conversions.inToCm(27), STANDARD_KELP_SPEED)); //Exit SB
			tasks.add(TurnTask.turn(90)); //turn CCW
			tasks.add(new DriveTask(Conversions.inToCm(19), STANDARD_KELP_SPEED)); //drive forward 
			tasks.add(TurnTask.turn(90)); //turn CCW
			tasks.add(new DriveTask(STANDARD_KELP_SPEED, 450));
			tasks.add(new DriveTask(Conversions.inToCm(18.25), -STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turn(-90));
		} else if (firstOrSecond == 2){
			tasks.add(new DriveTask(-100, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turn(90)); //turn CCW
			tasks.add(new DriveTask(STANDARD_KELP_SPEED, 450));
			tasks.add(new DriveTask(1232, -STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turn(90));
		} else {
			System.out.println("ERROR: firstOrSecond must be either 1 or 2.");
		}




		location = Location.FIRST_KELP; // Robot is in starting position for block grabbing
		offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		heading = Direction.EAST; 
		return tasks;
	}

	public static List<Task> getKelpChain() throws TaskException {
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();

		// Store robot state as the chain builds
		if(location != Location.FIRST_KELP // Robot is in starting position for block grabbing
				|| offset != Direction.CENTER
				|| heading != Direction.EAST // Robot is facing down
				) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");

		tasks.add(GrabTask.getTask());




		offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		heading = Direction.WEST;
		return tasks;
	}


	public static List<Task> returnKelpChain() throws TaskException{

		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();

		// Store robot state as the chain builds
		if((location != Location.FIRST_KELP || location != Location.SECOND_KELP)
				|| offset != Direction.CENTER
				|| heading != Direction.SOUTH
				) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");


		tasks.add(TurnTask.turn(90));
		tasks.add(new DriveTask(STANDARD_KELP_SPEED, 450));
		tasks.add(new DriveTask(-100, STANDARD_KELP_SPEED));
		tasks.add(TurnTask.turn(90));
		tasks.add(ReleaseTask.getTask());


		location = Location.DROPOFF;
		heading = Direction.WEST;



		return tasks;

	}

}
