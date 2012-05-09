package utils.pathfinding;

import utils.Constants.Location;
import utils.vision.Block;

public class BlockTask implements Task{
	private Location location;
	
	BlockTask(Location location) {
		for(Location testLocation : Location.getBlockLocations()){
			if(location == testLocation){
				this.location = testLocation;
				break;
			}
		}
	}
	/**
	 * 
	 * @return The location that the robot was in when this task was queued.
	 */
	public Location getLocation() {
		return location;
	}
	
	public Block getBlock() {
		return 	Block.getBlock();
	}
}
