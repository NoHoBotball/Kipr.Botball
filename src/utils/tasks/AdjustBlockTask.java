package utils.tasks;

import utils.pathfinding.Location;
import utils.vision.Block;

public class AdjustBlockTask extends Task {
	public Block block;
	
	public AdjustBlockTask(Block block) {
		this.block = block;
	}
	
	public AdjustBlockTask(Location location) {
		for(int index = 0; index < Location.getBlockLocations().length; index++){
			if(location == Location.getBlockLocations()[index]){
				block = Block.getBlock(index);
				break;
			}
		}
	}
	
	public Block getBlock() {
		return block;
	}
	
}
