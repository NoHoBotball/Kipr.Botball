package utils.pathfinding;

import java.util.ArrayList;
import java.util.List;

import utils.Constants;
import utils.Constants.Block;
import utils.Constants.BotLocation;
import utils.Constants.Direction;

public abstract class Task {
	
	public static List<Task> getTaskChain(Block[] blocks, int startingBlock) {
		
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();
		
		// Store robot state as the chain builds
		BotLocation location = BotLocation.GATHER_START; // Robot is in starting position for block grabbing
		Direction offset = Direction.CENTER; // Robot is on exact spot; different values define where the bot is relative to a block's center position
		int heading = 270; // Robot is facing down
		
		// Get block path order
		BotLocation[] destination = new BotLocation[3 - startingBlock];
		
		for (int i = startingBlock; i < 3; i++) {
			int priority = blocks[i].ordinal() - startingBlock;

			destination[priority] = BotLocation.getBlockLocations()[i];
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
							tasks.add(new TurnTask(90 - heading, Constants.STANDARD_SPEED)); // Turn to face up
							tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
							offset = Direction.NORTH; heading = 90;
							break;
						case BLOCK_SIDE:
							if (offset != Direction.CENTER && offset != Direction.NORTH) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(90 - heading, Constants.STANDARD_SPEED)); // Turn to face up
							tasks.add(DriveTask.getMoveToCenterTask(botCentered)); // Drive up
							tasks.add(new TurnTask(-90, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
							offset = Direction.EAST; heading = 0;
							break;
					}
					break;
				case BLOCK_CORNER:
					switch (location) {
						case GATHER_START:
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2 - Constants.BOT_OFFSET, Constants.STANDARD_SPEED)); // Drive to cube
							heading = 0;
							break;
						case BLOCK_FENCE:
							if (offset != Direction.CENTER && offset != Direction.SOUTH) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(270 - heading, Constants.STANDARD_SPEED)); // Turn to face down
							tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
							offset = Direction.SOUTH; heading = 270;
							break;
						case BLOCK_SIDE:
							if (offset != Direction.CENTER && offset != Direction.EAST) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(0 - heading, Constants.STANDARD_SPEED)); // Turn to face right
							tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
							offset = Direction.EAST; heading = 0;
							break;
					}
					break;
				case BLOCK_SIDE:
					switch (location) {
						case GATHER_START:
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(new DriveTask(Constants.CUBE_DISTANCE/2 - Constants.BOT_OFFSET, Constants.STANDARD_SPEED)); // Drive to cube
							heading = 180;
							break;
						case BLOCK_FENCE:
							if (offset != Direction.CENTER && offset != Direction.WEST) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(180 - heading, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(DriveTask.getMoveToCenterTask(botCentered)); // Drive left
							tasks.add(new TurnTask(90, Constants.STANDARD_SPEED)); // Turn to face down
							tasks.add(DriveTask.getMoveCubeDistanceTask(true)); // Drive to cube
							offset = Direction.SOUTH; heading = 270;
							break;
						case BLOCK_CORNER:
							if (offset != Direction.CENTER && offset != Direction.WEST) {
								tasks.add(DriveTask.getHalfCubeTask());
								botCentered = true;
							}
							tasks.add(new TurnTask(180 - heading, Constants.STANDARD_SPEED)); // Turn to face left
							tasks.add(DriveTask.getMoveCubeDistanceTask(botCentered)); // Drive to cube
							offset = Direction.WEST; heading = 180;
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
