package utils.vision;

import utils.Constants.Block;

public class BlockTrack {
	
	public Block getBlock(Blob blob){
		Block block;
		
		Blob red = new Blob(Block.RED.getChannel(), 0);
		Blob yellow = new Blob(Block.YELLOW.getChannel(), 0);
		Blob blue = new Blob(Block.BLUE.getChannel(), 0);
		
		                                                                    block = Block.RED;
		if(yellow.score() > red.score() && yellow.score() > red   .score()) block = Block.YELLOW;
		if(blue  .score() > red.score() && blue  .score() > yellow.score()) block = Block.BLUE;
		
		return block;
	}
}
