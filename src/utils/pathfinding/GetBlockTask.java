package utils.pathfinding;

import utils.Constants.Location;
import utils.vision.Block;

public class GetBlockTask extends Task{
	private Location location;
	
	GetBlockTask(Location location) {
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
