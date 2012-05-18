package utils.tasks;

import utils.pathfinding.Location;
import utils.vision.Block;

public class SeeBlockTask extends Task{
	private Location location;
	
	public SeeBlockTask(Location location) {
		this.location = location;
	}

	/**
	 * 
	 * @return The location that the robot was in when this task was queued.
	 */
	public Location getLocation() {
		return location;
	}
	
	public Block getBlock() {
		return 	Block.seeBlock();
	}
}
