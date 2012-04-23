package utils.pathfinding;

import utils.Constants.BotLocation;

public class KelpTask implements Task{
	private BotLocation location;
	
	KelpTask(BotLocation location) {
		for(BotLocation testLocation : BotLocation.getBlockLocations()){
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
	public BotLocation getLocation() {
		return location;
	}
	
}
