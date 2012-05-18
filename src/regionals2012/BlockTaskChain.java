package regionals2012;

import java.util.ArrayList;
import java.util.List;

import utils.tasks.AdjustBlockTask;
import utils.tasks.DriveTask;
import utils.tasks.ETDriveTask;
import utils.tasks.SeeBlockTask;
import utils.tasks.GrabTask;
import utils.tasks.ListTask;
import utils.tasks.StackBlockTask;
import utils.tasks.Task;
import utils.tasks.TaskException;
import utils.tasks.TurnTask;
import utils.BlockConstants;
import utils.GameConstants;
import utils.pathfinding.Location;
import utils.pathfinding.Direction;
import utils.vision.Block;

/**
 * Put all tasks chains to be generated throughout the duration
 * of the 2012 Botball Greater LA regional competition in this
 * class as static methods.
 *
 */
public class BlockTaskChain implements GameConstants, BlockConstants {
	
	static int startingBlock = 0;
	static int blocksGrabbed = 0;

	// Store robot state as the chain builds
	private static Block[] blocks = new Block[3];
	private static Location location = Location.GAME_START; // Robot is in starting position for block grabbing
	private static Direction heading = Direction.NORTH; // Robot is facing up
	private static List<Task> tasks;
	private static List<Task> _tasks;
	
	
	
	/* "Master" sequences */
	
	public static List<Task> openingMoves() throws TaskException{
		saveTasks();
		
		//Make sure robot is in correct position
		checkPos(Location.GAME_START, Direction.NORTH);
		
		//Initialize default speed values but don't move
		turn(heading, STANDARD_TURN_SPEED);
		drive(0, STANDARD_SPEED);

		//Move to CENTER
		drive(-STARTING_DISTANCE_VERTICAL);//drive backwards
		turn(Direction.WEST);
		drive(-STARTING_DISTANCE_HORIZONTAL);//drive to center of field
		location = Location.CENTER;
		
		//Get the color of the first block
		seeBlock(Location.BLOCK_SIDE);
		adjust(Location.BLOCK_SIDE);
		
		//Go to and get the color of the second block
		toFence();
		seeBlock();
		adjust();
		
		return restoreTasks(tasks);
	}
	
	public static List<Task> getBlockOrder() throws TaskException {
		saveTasks();

		checkPos(Location.BLOCK_FENCE, Direction.SOUTH);
		
		//drive BLOCK_CORNER in order to see second block
		drive(CUBE_DIST_LONG);
		location = Location.BLOCK_CORNER;
		
		//"see" second block
		seeBlock();
		adjust();
		
		//determine order of blocks
		switch(Block.getBlock(0)) {
			case RED:
				if(Block.getBlock(1) == Block.YELLOW) Block.setBlock(2, Block.BLUE);
				else                                  Block.setBlock(2, Block.YELLOW);
			case YELLOW:
				if(Block.getBlock(1) == Block.RED) Block.setBlock(2, Block.BLUE);
				else                               Block.setBlock(2, Block.RED);
			case BLUE:
				if(Block.getBlock(1) == Block.RED) Block.setBlock(2, Block.YELLOW);
				else                               Block.setBlock(2, Block.RED);
		}
		blocks = Block.getBlocks();
		
		return restoreTasks(tasks);
	}

	public static List<Task> gatherBlocks(Block[] blocks, int startingBlock) throws TaskException{
		BlockTaskChain.blocks = blocks;
		return gatherBlocks(startingBlock);
	}
	
	public static List<Task> gatherBlocks(int startingBlock) throws TaskException{

		saveTasks();
		
		checkPos(Location.BLOCK_CORNER, Direction.SOUTH);
		BlockTaskChain.startingBlock = startingBlock;
		
		// Get block path order
		Location[] destination = new Location[blocks.length];
		
		for (int i = 0; i < blocks.length; i++) {
			int priority = blocks[i].ordinal();

			destination[priority] =
					Location.getBlockLocations()[i];
		}
		
		for (int i = startingBlock; i < destination.length; i++) {
			switch(destination[i]) {
				case BLOCK_FENCE:
					toFence();
					break;
				case BLOCK_CORNER:
					toCorner();
					break;
				case BLOCK_SIDE:
					toSide();
					break;
			}
			
			grabBlock();			
		}
		return restoreTasks(tasks);		
	}
	
	public static List<Task> retreat() throws TaskException {
		saveTasks();
		
		if(blocksGrabbed < 3 - startingBlock) throw new TaskException("You may not retreat until you accomplish your mission, Soldier.");

		toStack();
		stackBlock();
		
		return restoreTasks(tasks);		
	}
	
	
	
	/* TaskChain "sub"-sequences */
	
	public static List<Task> toFence() throws TaskException {
		saveTasks();
		switch(location) {
		case BLOCK_FENCE:
			break;
		case BLOCK_CORNER:
			turn(Direction.SOUTH);
			drive(-CUBE_DIST_LONG);
			break;
		case BLOCK_SIDE:
			turn(Direction.SOUTH);
			drive(-CUBE_DIST_SHORT);
			turn(Direction.EAST);
			drive(CUBE_DIST_SHORT);
			break;
		case CENTER:
			turn(Direction.EAST);
			drive(CUBE_DIST_SHORT);
			break;
		case STACK:
			turn(Direction.SOUTH);
			drive(-STACK_DIST);
			turn(Direction.EAST);
			drive(STARTING_DISTANCE_HORIZONTAL);
		}
		location = Location.BLOCK_FENCE;
		return restoreTasks(tasks);
	}
	
	public static List<Task> toCorner() throws TaskException {
		saveTasks();
		switch(location) {
		case BLOCK_FENCE:
			turn(Direction.SOUTH);
			drive(CUBE_DIST_LONG);
			break;
		case BLOCK_CORNER:
			break;
		case BLOCK_SIDE:
			toCenter();
			toCorner();
			break;
		case CENTER:
			turn(Direction.SOUTH);
			drive(CUBE_DIST);
			break;
		case STACK:
			toCenter();
			toCorner();
			break;
		}
		location = Location.BLOCK_CORNER;
		return restoreTasks(tasks); 
	}
	
	public static List<Task> toSide() throws TaskException {
		saveTasks();
		switch(location) {
		case BLOCK_FENCE:
			toCenter();
			toSide();
			break;
		case BLOCK_CORNER:
			turn(Direction.SOUTH);
			drive(-(CUBE/2 + CLAW_FREE));
			turn(Direction.EAST);
			drive(-CUBE_DIST_SHORT);
			break;
		case BLOCK_SIDE:
			break;
		case CENTER:
			turn(Direction.SOUTH);
			drive(CUBE_DIST_SHORT);
			break;
		case STACK:
			toCenter();
			toSide();
			break;
		}
		location = Location.BLOCK_SIDE;
		return restoreTasks(tasks);
	}
	
	public static List<Task> toCenter() throws TaskException {
		saveTasks();
		switch(location) {
		case BLOCK_FENCE:
			turn(Direction.EAST);
			drive(-CUBE_DIST_SHORT);
			break;
		case BLOCK_CORNER:
			toFence();
			toCenter();
			break;
		case BLOCK_SIDE:
			turn(Direction.SOUTH);
			drive(-CUBE_DIST_SHORT);
			break;
		case CENTER:
			break;
		case STACK:
			turn(Direction.SOUTH);
			drive(-STACK_DIST);
			turn(Direction.EAST);
			drive(STARTING_DISTANCE_HORIZONTAL - CUBE_DIST_SHORT);
			break;
		}
		location = Location.CENTER;
		return restoreTasks(tasks); 
	}
	
	public static List<Task> toStack() throws TaskException {
		saveTasks();
		switch(location) {
		case BLOCK_FENCE:
			toCenter();
			toStack();
			break;
		case BLOCK_CORNER:
			toCenter();
			toStack();
			break;
		case BLOCK_SIDE:
			toCenter();
			toStack();
			break;
		case CENTER:
			turn(Direction.EAST);
			drive(-(STARTING_DISTANCE_HORIZONTAL - CUBE_DIST_SHORT));
			break;
		case STACK:
			break;
		}
		location = Location.STACK;
		return restoreTasks(tasks);
	}
	
	public static List<Task> grabBlock() throws TaskException {
		saveTasks();
		grabBlock();
		return restoreTasks(tasks);		
	}

	public static List<Task> stackBlock() throws TaskException {
		saveTasks();
		stack();
		return restoreTasks(tasks);		
	}

	
	
	/* ports for tasks */
	
	private static void saveTasks() {
		_tasks = tasks;
		tasks = new ArrayList<Task>();
	}
	
	private static List<Task> restoreTasks(List<Task> tasks) {
		BlockTaskChain.tasks = _tasks;
		tasks.add(new ListTask(tasks));
		return tasks;
	}
	
	private static void checkPos(Location location) throws TaskException {
		if(BlockTaskChain.location != location
		) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");
	}
	
	private static void checkPos(Location location, Direction heading) throws TaskException {
		if(BlockTaskChain.location != location
		|| BlockTaskChain.heading != heading
		) throw new TaskException("Robot must be in the correct position and have the correct heading to generate this task chain");
	}
	
	private static void add(Task task) {
		tasks.add(task);
	}
	
	private static void drive(double distance, double speed) {
		add(new DriveTask(distance, speed));
	}
	
	private static void drive(double distance) throws TaskException {
		add(new DriveTask(distance));
	}
	
	private static void driveET(double speed, int ET_VALUE) {
		add(new ETDriveTask(speed, ET_VALUE));
	}
	
	private static void grab() {
		add(GrabTask.getTask());
		blocksGrabbed++;
	}
	
	private static void turn(Direction heading) {
		add(new TurnTask(BlockTaskChain.heading, heading));
		BlockTaskChain.heading = heading;
	}
	
	private static void turn(Direction heading, double speed) {
		add(new TurnTask(BlockTaskChain.heading, heading, speed));
		BlockTaskChain.heading = heading;
	}
	
	private static void adjust(Location location) {
		add(new AdjustBlockTask(location));
	}
	
	private static void adjust() {
		add(new AdjustBlockTask(location));
	}
	
	private static void seeBlock(Location location) {
		add(new SeeBlockTask(location));
	}
	
	private static void seeBlock() {
		add(new SeeBlockTask(location));
	}
	
	private static void stack() {
		add(StackBlockTask.getTask());
	}
	
}
