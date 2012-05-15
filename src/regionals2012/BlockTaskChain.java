package regionals2012;

import java.util.ArrayList;
import java.util.List;

import utils.tasks.AdjustBlockTask;
import utils.tasks.DriveTask;
import utils.tasks.GetBlockTask;
import utils.tasks.GrabTask;
import utils.tasks.Task;
import utils.tasks.TaskException;
import utils.tasks.TurnTask;
import utils.BlockConstants;
import utils.Conversions;
import utils.GameConstants;
import utils.pathfinding.Location;
import utils.pathfinding.Direction;
import utils.vision.Block;

/**
 * Put all tasks chains to be generated throughout the duration
 * of the 2012 Bobtall Greater LA regional competition in this
 * class as static methods.
 *
 */
public class BlockTaskChain implements GameConstants, BlockConstants {
	//static Block[] blocks = null;
	static int startingBlock = 0;
	
	public BlockTaskChain() {}
	
	
	// Store robot state as the chain builds
	private static Location location = Location.GAME_START; // Robot is in starting position for block grabbing
	private static Direction offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
	private static Direction heading = Direction.SOUTH; // Robot is facing down

	public static List<Task> openingMoves() throws TaskException{
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();
		
		if(location != Location.GAME_START // Robot is in starting position for block grabbing
				   || offset != Direction.CENTER // Robot is on exact spot; different values define where the bot is relative to a block's center position
				   || heading != Direction.SOUTH // Robot is facing down
				   ) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");
		
		
		//TODO: Fix distance 
		
		// Move into place
		tasks.add(new DriveTask(STARTING_DISTANCE_VERTICAL, STANDARD_SPEED));
		tasks.add(new TurnTask(90, STANDARD_SPEED));
		tasks.add(new DriveTask(STARTING_DISTANCE_HORIZONAL, STANDARD_SPEED));
		tasks.add(new TurnTask(-90, STANDARD_SPEED));
		//tasks.add(new )
		
		location = Location.BLOCK_FENCE; // Robot is in starting position for block grabbing
		offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		heading = Direction.SOUTH; // Robot is facing down
		
		tasks.add(new GetBlockTask(location));
		tasks.add(new AdjustBlockTask(Block.getBlock(location)));
		
		return tasks;
	}
	
	public static List<Task> grabFirstBlock() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<Task> getBlockOrder() throws TaskException {
		List<Task> tasks = new ArrayList<Task>();

		// Store robot state as the chain builds
		if(location != Location.BLOCK_FENCE // Robot is in starting position for block grabbing
					|| offset != Direction.CENTER
					|| heading != Direction.SOUTH // Robot is facing down
		   ) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");
		
		//TODO: drive to see second block
		//TODO: "see" second block
		//TODO: determine order of blocks
		
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

			destination[priority] = 
					Location.getBlockLocations()[i];
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
							tasks.add(new TurnTask(heading.degreesTo(Direction.NORTH), STANDARD_SPEED)); // Turn to face up
							tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
							offset = heading = Direction.NORTH;
							break;
						case BLOCK_SIDE:
							if (offset != Direction.CENTER && offset != Direction.NORTH) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(heading.degreesTo(Direction.NORTH), STANDARD_SPEED)); // Turn to face up
							tasks.add(DriveTask.getMoveToCenterTask(botCentered)); // Drive up
							tasks.add(new TurnTask(-90, STANDARD_SPEED)); // Turn to face right
							tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
							offset = heading = Direction.EAST;
							break;
					}
					break;
				case BLOCK_CORNER:
					switch (location) {
						case GATHER_START:
							tasks.add(new TurnTask(90, STANDARD_SPEED)); // Turn to face right
							tasks.add(new DriveTask(CUBE_DISTANCE/2 - BOT_OFFSET, STANDARD_SPEED)); // Drive to cube
							heading = Direction.EAST;
							break;
						case BLOCK_FENCE:
							if (offset != Direction.CENTER && offset != Direction.SOUTH) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(heading.degreesTo(Direction.SOUTH), STANDARD_SPEED)); // Turn to face down
							tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
							offset = heading = Direction.SOUTH;
							break;
						case BLOCK_SIDE:
							if (offset != Direction.CENTER && offset != Direction.EAST) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(heading.degreesTo(Direction.EAST), STANDARD_SPEED)); // Turn to face right
							tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
							offset = heading = Direction.EAST;
							break;
					}
					break;
				case BLOCK_SIDE:
					switch (location) {
						case GATHER_START:
							tasks.add(new TurnTask(90, STANDARD_SPEED)); // Turn to face left
							tasks.add(new DriveTask(CUBE_DISTANCE/2 - BOT_OFFSET, STANDARD_SPEED)); // Drive to cube
							heading = Direction.WEST;
							break;
						case BLOCK_FENCE:
							if (offset != Direction.CENTER && offset != Direction.WEST) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(heading.degreesTo(Direction.WEST), STANDARD_SPEED)); // Turn to face left
							tasks.add(DriveTask.getMoveToCenterTask(botCentered)); // Drive left
							tasks.add(new TurnTask(90, STANDARD_SPEED)); // Turn to face down
							tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
							offset = heading = Direction.SOUTH;
							break;
						case BLOCK_CORNER:
							if (offset != Direction.CENTER && offset != Direction.WEST) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(heading.degreesTo(Direction.WEST), STANDARD_SPEED)); // Turn to face left
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
