package utils.pathfinding;

import utils.Constants.BotLocation;
import utils.vision.Block;

public class BlockTask implements Task{
	private BotLocation location;
	private Block block;
	
	BlockTask(BotLocation location) {
		for(BotLocation testLocation : BotLocation.getBlockLocations()){
			if(location == testLocation){
				this.location = testLocation;
				break;
			}
		}
		block = Block.getBlock();
	}
	/**
	 * 
	 * @return The location that the robot was in when this task was queued.
	 */
	public BotLocation getLocation() {
		return location;
	}
	
	public Block getBlock() {
		return block;
	}
}
