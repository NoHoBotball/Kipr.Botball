package utils.pathfinding;

import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Constants.Block;

public abstract class Task {
	
	public static List<Task> getTaskChain(Block[] blocks, boolean skipFirstBlock) {
		
		// Default task values (we can mess with these)
		int defSpeed = 100;
		
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();
		
		// Store robot state as the chain builds
		int location = -1; // Robot is in starting position for block grabbing
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
			
			switch (locations[i]) {
				case 0: // Location near fence
					switch (location) {
						case 1:
							tasks.add(new TurnTask(90 - rotation, defSpeed)); // Turn to face up
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							rotation = 90;
							break;
						case 2:
							tasks.add(new TurnTask(90 - rotation, defSpeed)); // Turn to face up
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE, defSpeed)); // Drive up
							tasks.add(new TurnTask(-90, defSpeed)); // Turn to face right
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							rotation = 0;
							break;
					}
					location = 0;
					break;
				case 1: // Location in corner
					switch (location) {
						case -1:
							tasks.add(new TurnTask(90, defSpeed)); // Turn to face right
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2, defSpeed)); // Drive to cube
							rotation = 0;
							break;
						case 0:
							tasks.add(new TurnTask(270 - rotation, defSpeed)); // Turn to face down
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							rotation = 270;
							break;
						case 2:
							tasks.add(new TurnTask(0 - rotation, defSpeed)); // Turn to face right
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							rotation = 0;
							break;
					}
					location = 1;
					break;
				case 2: // Location on side
					switch (location) {
						case -1:
							tasks.add(new TurnTask(90, defSpeed)); // Turn to face left
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2, defSpeed)); // Drive to cube
							rotation = 180;
							break;
						case 0:
							tasks.add(new TurnTask(180 - rotation, defSpeed)); // Turn to face left
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE, defSpeed)); // Drive left
							tasks.add(new TurnTask(90, defSpeed)); // Turn to face down
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE, defSpeed)); // Drive down
							rotation = 270;
							break;
						case 1:
							tasks.add(new TurnTask(180 - rotation, defSpeed)); // Turn to face left
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							rotation = 180;
							break;
					}
					location = 2;
					break;
			}
			
			tasks.add(new GrabTask()); // Grab cube
			tasks.add(new DriveTask(Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube location
			
		}
		
		return tasks;
		
	}
	
}
