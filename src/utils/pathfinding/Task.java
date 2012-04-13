package utils.pathfinding;

import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Constants.Block;

public abstract class Task {
	
	public static List<Task> getTaskChain(Block[] blocks, boolean skipFirstBlock) {
		
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();
		
		// Store robot state as the chain builds
		int location = -1; // Robot is in starting position for block grabbing
		int offset = 0; // Robot is on exact spot; different values define where the bot is relative to a block's center position;
		                // (1 = NORTH, 2 = WEST, 3 = SOUTH, 4 = EAST)
		int rotation = 270; // Robot is facing down
		
		// Get block path order
		int[] locations = new int[skipFirstBlock ? 2 : 3];
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
			
			locations[skipFirstBlock ? index - 1 : index] = i;
		}
		
		// TODO: Implement the rest of the pathfinding/make it actually practical
		
		for (int i = 0; i < locations.length; i++) {
			
			boolean flag = false; // Used for handling special cases
			
			switch (locations[i]) {
				case 0: // Location near fence
					switch (location) {
						case 1:
							if (offset != 0 && offset != 1) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(90 - rotation, Constants.STANDARD_SPEED)); // Turn to face up
							tasks.add(DriveTask.getMoveCubeDistanceTask(flag)); // Drive to cube
							offset = 1; rotation = 90;
							break;
						case 2:
							if (offset != 0 && offset != 1) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(90 - rotation, Constants.STANDARD_SPEED)); // Turn to face up
							tasks.add(DriveTask.getMoveToCenterTask(flag)); // Drive up
							tasks.add(new TurnTask(-90, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
							offset = 2; rotation = 0;
							break;
					}
					location = 0;
					break;
				case 1: // Location in corner
					switch (location) {
						case -1:
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2 - Constants.BOT_OFFSET, Constants.STANDARD_SPEED)); // Drive to cube
							rotation = 0;
							break;
						case 0:
							if (offset != 0 && offset != 3) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(270 - rotation, Constants.STANDARD_SPEED)); // Turn to face down
							tasks.add(DriveTask.getMoveCubeDistanceTask(flag)); // Drive to cube
							offset = 3; rotation = 270;
							break;
						case 2:
							if (offset != 0 && offset != 2) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(0 - rotation, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(DriveTask.getMoveCubeDistanceTask(flag)); // Drive to cube
							offset = 2; rotation = 0;
							break;
					}
					location = 1;
					break;
				case 2: // Location on side
					switch (location) {
						case -1:
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2 - Constants.BOT_OFFSET, Constants.STANDARD_SPEED)); // Drive to cube
							rotation = 180;
							break;
						case 0:
							if (offset != 0 && offset != 4) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(180 - rotation, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(DriveTask.getMoveToCenterTask(flag)); // Drive left
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face down
							tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
							offset = 3; rotation = 270;
							break;
						case 1:
							if (offset != 0 && offset != 4) {
								tasks.add(DriveTask.getHalfCubeTask());
								flag = true;
							}
							tasks.add(new TurnTask(180 - rotation, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(DriveTask.getMoveCubeDistanceTask(flag)); // Drive to cube
							offset = 4; rotation = 180;
							break;
					}
					location = 2;
					break;
			}
			
			tasks.add(new GrabTask()); // Grab cube
			
		}
		
		return tasks;
		
	}
	
}
