package utils.pathfinding;

import utils.Constants;
import utils.Constants.Location;

public class KelpTask implements Task{
	private Location location;
	
	KelpTask(Location location) {
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
	
}
