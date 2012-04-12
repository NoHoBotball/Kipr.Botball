package utils;

import java.util.ArrayList;
import java.util.List;

import utils.Constants.Block;

public class Task {
	
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
							tasks.add(new Task(TaskType.TURN, 90 - rotation, defSpeed)); // Turn to face up
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							tasks.add(new Task(TaskType.GRAB)); // Grab cube
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube location
							rotation = 90; location = 0;
							break;
						case 2:
							tasks.add(new Task(TaskType.TURN, 90 - rotation, defSpeed)); // Turn to face up
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE, defSpeed)); // Drive up
							tasks.add(new Task(TaskType.TURN, -90, defSpeed)); // Turn to face right
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							tasks.add(new Task(TaskType.GRAB)); // Grab cube
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube location
							rotation = 0; location = 0;
							break;
					}
					break;
				case 1: // Location in corner
					switch (location) {
						case -1:
							tasks.add(new Task(TaskType.TURN, 90, defSpeed)); // Turn to face right
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE/2, defSpeed)); // Drive to cube
							tasks.add(new Task(TaskType.GRAB)); // Grab cube
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube
							rotation = 0; location = 1;
							break;
						case 0:
							tasks.add(new Task(TaskType.TURN, 270 - rotation, defSpeed)); // Turn to face down
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							tasks.add(new Task(TaskType.GRAB)); // Grab cube
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube
							rotation = 270; location = 1;
							break;
						case 2:
							tasks.add(new Task(TaskType.TURN, 0 - rotation, defSpeed)); // Turn to face right
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							tasks.add(new Task(TaskType.GRAB)); // Grab cube
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube
							rotation = 0; location = 1;
							break;
					}
					break;
				case 2: // Location on side
					switch (location) {
						case -1:
							tasks.add(new Task(TaskType.TURN, 90, defSpeed)); // Turn to face left
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE/2, defSpeed)); // Drive to cube
							tasks.add(new Task(TaskType.GRAB)); // Grab cube
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube
							rotation = 180; location = 2;
							break;
						case 0:
							tasks.add(new Task(TaskType.TURN, 180 - rotation, defSpeed)); // Turn to face left
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE, defSpeed)); // Drive left
							tasks.add(new Task(TaskType.TURN, 90, defSpeed)); // Turn to face down
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE, defSpeed)); // Drive down
							tasks.add(new Task(TaskType.GRAB)); // Grab cube
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube
							rotation = 270; location = 2;
							break;
						case 1:
							tasks.add(new Task(TaskType.TURN, 180 - rotation, defSpeed)); // Turn to face left
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_DISTANCE, defSpeed)); // Drive to cube
							tasks.add(new Task(TaskType.GRAB)); // Grab cube
							tasks.add(new Task(TaskType.DRIVE, Constants.CUBE_WIDTH/2, defSpeed)); // Center on cube
							rotation = 180; location = 2;
							break;
					}
					break;
			}
			
		}
		
		return tasks;
		
	}
	
	private TaskType type;
	private int speed;
	private int amount;
	
	/**
	 * Creates a new robot task.
	 * 
	 * @param type type of task
	 * @param speed the speed to execute this task
	 * @param amount amount associated with this task
	 */
	public Task (TaskType type, int speed, int data) {
		this.type = type;
		this.speed = speed;
		this.amount = data;
	}
	
	/**
	 * Creates a new robot task.
	 * 
	 * @param type type of task
	 */
	public Task (TaskType type) {
		this (type, 0, 0);
	}
	
	/**
	 * Gets what type of task this object represents.
	 * 
	 * @return task type
	 */
	public TaskType getType() {
		return type;
	}
	
	/**
	 * Gets the speed of this task.
	 * 
	 * @return speed
	 */
	public int setSpeed() {
		return speed;
	}
	
	/**
	 * Returns the amount associated with this task.
	 * 
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * Represents a type of task for a robot to perform.
	 */
	public static enum TaskType {
		DRIVE,
		TURN,
		GRAB;
	}
	
}
