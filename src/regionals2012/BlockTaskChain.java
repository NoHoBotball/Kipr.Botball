package regionals2012;

import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Constants.Location;
import utils.Constants.Direction;
import utils.pathfinding.DriveTask;
import utils.pathfinding.GrabTask;
import utils.pathfinding.ListTask;
import utils.pathfinding.Task;
import utils.pathfinding.TaskException;
import utils.pathfinding.TurnTask;
import utils.vision.Block;

/**
 * Put all tasks chains to be generated throughout the duration
 * of the 2012 Bobtall Greater LA regional competition in this
 * class as static methods.
 *
 */
public class BlockTaskChain {
	static Block[] blocks = null;
	static int startingBlock = 0;
	
	public BlockTaskChain() {}

	public static List<Task> getTaskChain() throws TaskException{
		List<Task> taskChain = new ArrayList<Task>();
		
		taskChain.add(new ListTask(getOpeningMovesChain()));
		taskChain.add(new ListTask(getBlockColorChain()));
		taskChain.add(new ListTask(getBlockGatherChain(blocks, startingBlock)));

		return taskChain;
	}
	
	
	//for testing puposes only
	public static List<Task> getTaskChain(Block[] blocks, int startingBlock) throws TaskException{
		List<Task> taskChain = new ArrayList<Task>();
		
		taskChain.add(new ListTask(getOpeningMovesChain()));
		taskChain.add(new ListTask(getBlockColorChain()));
		taskChain.add(new ListTask(getBlockGatherChain(blocks, startingBlock)));

		return taskChain;
	}
	
	
	// Store robot state as the chain builds
	private static Location location = Location.GAME_START; // Robot is in starting position for block grabbing
	private static Direction offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
	private static Direction heading = Direction.SOUTH; // Robot is facing down

	private static List<Task> getOpeningMovesChain() throws TaskException{
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();
		
		if(location != Location.GAME_START // Robot is in starting position for block grabbing
				   || offset != Direction.CENTER // Robot is on exact spot; different values define where the bot is relative to a block's center position
				   || heading != Direction.SOUTH // Robot is facing down
				   ) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");
		
		
		//TODO: Fix distance constants.
		
		tasks.add(new DriveTask(Constants.STARTING_DISTANCE_VERTICAL, Constants.STANDARD_SPEED));
		tasks.add(new TurnTask(90, Constants.STANDARD_SPEED));
		tasks.add(new DriveTask(Constants.STARTING_DISTANCE_HORIZONAL, Constants.STANDARD_SPEED));
		tasks.add(new TurnTask(-90, Constants.STANDARD_SPEED));
		
		location = Location.BLOCK_FENCE; // Robot is in starting position for block grabbing
		offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		heading = Direction.SOUTH; // Robot is facing down
		return tasks;
	}
	
	private static List<Task> getBlockColorChain() throws TaskException {
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
	
	
	private static List<Task> getBlockGatherChain(Block[] blocks, int startingBlock) throws TaskException{
		
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
}
