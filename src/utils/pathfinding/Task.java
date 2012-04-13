package utils.pathfinding;

import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Constants.Block;
import utils.Constants.BotLocation;
import utils.Constants.Direction;

public abstract class Task {
	
	public static List<Task> getTaskChain(Block[] blocks, boolean skipFirstBlock) {
		
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();
		
		// Store robot state as the chain builds
		BotLocation location = BotLocation.GATHER_START; // Robot is in starting position for block grabbing
		Direction offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		int rotation = 270; // Robot is facing down
		
		// Get block path order
		BotLocation[] locations = new BotLocation[skipFirstBlock ? 2 : 3];
		for (int i = (skipFirstBlock ? 1 : 0); i < 3; i++) {
			int index = 0;
			
			switch (blocks[i]) {
				case RED:
					index = 0;
					break;
				case YELLOW:
					index = 1;
					break;
				case BLUE:
					index = 2;
					break;
			}
			
			locations[skipFirstBlock ? index - 1 : index] = BotLocation.getBlockLocations()[i];
		}
		
		// TODO: Implement the rest of the pathfinding/make it actually practical
		
		for (int i = 0; i < locations.length; i++) {
			
			boolean flag = false; // Used for handling special cases
			
			switch (locations[i]) {
				case BLOCK_FENCE:
					switch (location) {
						case BLOCK_CORNER:
							if (offset != Direction.CENTER && offset != Direction.NORTH) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(90 - rotation, Constants.STANDARD_SPEED)); // Turn to face up
							tasks.add(DriveTask.getMoveCubeDistanceTask(flag)); // Drive to cube
							offset = Direction.NORTH; rotation = 90;
							break;
						case BLOCK_SIDE:
							if (offset != Direction.CENTER && offset != Direction.NORTH) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(90 - rotation, Constants.STANDARD_SPEED)); // Turn to face up
							tasks.add(DriveTask.getMoveToCenterTask(flag)); // Drive up
							tasks.add(new TurnTask(-90, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
							offset = Direction.EAST; rotation = 0;
							break;
					}
					location = BotLocation.BLOCK_FENCE;
					break;
				case BLOCK_CORNER:
					switch (location) {
						case GATHER_START:
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2 - Constants.BOT_OFFSET, Constants.STANDARD_SPEED)); // Drive to cube
							rotation = 0;
							break;
						case BLOCK_FENCE:
							if (offset != Direction.CENTER && offset != Direction.SOUTH) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(270 - rotation, Constants.STANDARD_SPEED)); // Turn to face down
							tasks.add(DriveTask.getMoveCubeDistanceTask(flag)); // Drive to cube
							offset = Direction.SOUTH; rotation = 270;
							break;
						case BLOCK_SIDE:
							if (offset != Direction.CENTER && offset != Direction.EAST) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(0 - rotation, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(DriveTask.getMoveCubeDistanceTask(flag)); // Drive to cube
							offset = Direction.EAST; rotation = 0;
							break;
					}
					location = BotLocation.BLOCK_CORNER;
					break;
				case BLOCK_SIDE:
					switch (location) {
						case GATHER_START:
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2 - Constants.BOT_OFFSET, Constants.STANDARD_SPEED)); // Drive to cube
							rotation = 180;
							break;
						case BLOCK_FENCE:
							if (offset != Direction.CENTER && offset != Direction.WEST) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(180 - rotation, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(DriveTask.getMoveToCenterTask(flag)); // Drive left
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face down
							tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
							offset = Direction.SOUTH; rotation = 270;
							break;
						case BLOCK_CORNER:
							if (offset != Direction.CENTER && offset != Direction.WEST) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(180 - rotation, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(DriveTask.getMoveCubeDistanceTask(flag)); // Drive to cube
							offset = Direction.WEST; rotation = 180;
							break;
					}
					location = BotLocation.BLOCK_SIDE;
					break;
			}
			
			tasks.add(GrabTask.getTask()); // Grab cube
			
		}
		
		return tasks;
		
	}
	
}
