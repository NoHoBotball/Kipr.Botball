package utils.tasks;

import utils.pathfinding.Location;
import utils.vision.Block;

public class GetBlockTask extends Task{
	private Location location;
	
	public GetBlockTask(Location location) {
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