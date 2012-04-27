package regionals2012;

import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Constants.Location;
import utils.Constants.Direction;
import utils.pathfinding.DriveTask;
import utils.pathfinding.GrabTask;
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
public class TaskChain {
	// Store robot state as the chain builds
	static Location location = Location.GAME_START; // Robot is in starting position for block grabbing
	static Direction offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
	static Direction heading = Direction.SOUTH; // Robot is facing down

	public static List<Task> getOpeningMovesChain() throws TaskException{
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();

		if(location != Location.GAME_START // Robot is in starting position for block grabbing
				|| offset != Direction.CENTER // Robot is on exact spot; different values define where the bot is relative to a block's center position
				|| heading != Direction.SOUTH // Robot is facing down
				) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");


		//TODO: Fill out and plan opening moves.


		location = Location.BLOCK_FENCE; // Robot is in starting position for block grabbing
		offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		heading = Direction.SOUTH; // Robot is facing down
		return tasks;
	}

	public static List<Task> getBlockColorChain() throws TaskException {
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();

		// Store robot state as the chain builds
		if(location != Location.BLOCK_FENCE // Robot is in starting position for block grabbing
				|| offset != Direction.CENTER
				|| heading != Direction.SOUTH // Robot is facing down
				) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");


		//TODO: Fill out and plan moves.


		offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		heading = Direction.SOUTH; // Robot is facing down
		return tasks;
	}


	public static List<Task> getBlockGatherChain(Block[] blocks, int startingBlock) throws TaskException{

		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();

		// Store robot state as the chain builds
		if((location != Location.BLOCK_FENCE && location != Location.BLOCK_CORNER)
				|| offset != Direction.CENTER
				|| heading != Direction.SOUTH
				) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");

		// Get block path order
		Location[] destination = new Location[3 - startingBlock];

		for (int i = startingBlock; i < 3; i++) {
			int priority = blocks[i].ordinal() - startingBlock;

			destination[priority] = Location.getBlockLocations()[i];
		}

		// TODO: Implement the rest of the pathfinding/make it actually practical

		for (int i = 0; i < destination.length; i++) {

			boolean botCentered = false; // Set to true to indicate the the bot has been re-centered on the location

			switch (destination[i]) {
			case BLOCK_FENCE:
				switch (location) {
				case BLOCK_CORNER:
					if (offset != Direction.CENTER && offset != Direction.NORTH) {
						tasks.add(DriveTask.getHalfCubeTask());
						botCentered = true;
					}
					tasks.add(new TurnTask(heading.degreesTo(Direction.NORTH), Constants.STANDARD_SPEED)); // Turn to face up
					tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
					offset = heading = Direction.NORTH;
					break;
				case BLOCK_SIDE:
					if (offset != Direction.CENTER && offset != Direction.NORTH) {
						tasks.add(DriveTask.getHalfCubeTask());
						botCentered = true;
					}
					tasks.add(new TurnTask(heading.degreesTo(Direction.NORTH), Constants.STANDARD_SPEED)); // Turn to face up
					tasks.add(DriveTask.getMoveToCenterTask(botCentered)); // Drive up
					tasks.add(new TurnTask(-90, Constants.STANDARD_SPEED)); // Turn to face right
					tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
					offset = heading = Direction.EAST;
					break;
				}
				break;
			case BLOCK_CORNER:
				switch (location) {
				case GATHER_START:
					tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face right
					tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2 - Constants.BOT_OFFSET, Constants.STANDARD_SPEED)); // Drive to cube
					heading = Direction.EAST;
					break;
				case BLOCK_FENCE:
					if (offset != Direction.CENTER && offset != Direction.SOUTH) {
						tasks.add(DriveTask.getHalfCubeTask());
						botCentered = true;
					}
					tasks.add(new TurnTask(heading.degreesTo(Direction.SOUTH), Constants.STANDARD_SPEED)); // Turn to face down
					tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
					offset = heading = Direction.SOUTH;
					break;
				case BLOCK_SIDE:
					if (offset != Direction.CENTER && offset != Direction.EAST) {
						tasks.add(DriveTask.getHalfCubeTask());
						botCentered = true;
					}
					tasks.add(new TurnTask(heading.degreesTo(Direction.EAST), Constants.STANDARD_SPEED)); // Turn to face right
					tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
					offset = heading = Direction.EAST;
					break;
				}
				break;
			case BLOCK_SIDE:
				switch (location) {
				case GATHER_START:
					tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face left
					tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2 - Constants.BOT_OFFSET, Constants.STANDARD_SPEED)); // Drive to cube
					heading = Direction.WEST;
					break;
				case BLOCK_FENCE:
					if (offset != Direction.CENTER && offset != Direction.WEST) {
						tasks.add(DriveTask.getHalfCubeTask());
						botCentered = true;
					}
					tasks.add(new TurnTask(heading.degreesTo(Direction.WEST), Constants.STANDARD_SPEED)); // Turn to face left
					tasks.add(DriveTask.getMoveToCenterTask(botCentered)); // Drive left
					tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face down
					tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
					offset = heading = Direction.SOUTH;
					break;
				case BLOCK_CORNER:
					if (offset != Direction.CENTER && offset != Direction.WEST) {
						tasks.add(DriveTask.getHalfCubeTask());
						botCentered = true;
					}
					tasks.add(new TurnTask(heading.degreesTo(Direction.WEST), Constants.STANDARD_SPEED)); // Turn to face left
					tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
					offset = heading = Direction.WEST;
					break;
				}
				break;
			}

			tasks.add(GrabTask.getTask()); // Grab cube
			location = destination[i]; // Update bot state

		}

		return tasks;

	}

	public static List<Task> getKelpOpeningChain() throws TaskException{
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();

		if(location != Location.GAME_START // Robot is in starting position for block grabbing
				|| offset != Direction.CENTER // Robot is on exact spot; different values define where the bot is relative to a block's center position
				//   || heading != Direction.WEST // Robot is facing down
				) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");


		//TODO: Fill out and plan opening moves.
		
		tasks.add(new DriveTask(1231, Constants.STANDARD_KELP_SPEED)); //Exit SB
		tasks.add(TurnTask.turn(90));
		tasks.add(new DriveTask (123124, Constants.STANDARD_KELP_SPEED));
		tasks.add(TurnTask.turn(90));
		tasks.add(new DriveTask(1234, Constants.STANDARD_KELP_SPEED));
		tasks.add(new DriveTask(Constants.STANDARD_KELP_SPEED, 450));
		tasks.add(new DriveTask(1232, -Constants.STANDARD_KELP_SPEED));
		tasks.add(TurnTask.turn(-90));
		
		


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
		
		tasks.add(new DriveTask(Constants.STANDARD_KELP_SPEED, 450));
		
		


		offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		heading = Direction.WEST;
		return tasks;
	}


	public static List<Task> getReturnKelpChain(int kelpNumber) throws TaskException{

		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();

		// Store robot state as the chain builds
		if((location != Location.FIRST_KELP || location != Location.SECOND_KELP)
				|| offset != Direction.CENTER
				|| heading != Direction.SOUTH
				) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");
		

		tasks.add(GrabTask.getTask()); // Grab cube
		location = Location.DROPOFF;
		heading = Direction.WEST;
		



		return tasks;

	}

}
