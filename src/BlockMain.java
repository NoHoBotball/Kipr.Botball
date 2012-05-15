import cbc.create.CreateConnectException;
import cbc.sensors.buttons.AButton;
import cbc.sensors.buttons.BButton;
import cbc.sensors.buttons.BlackButton;

import regionals2012.BlockTaskChain;
import regionals2012.TaskRunner;
import robot.BlockRobot;

import utils.tasks.TaskException;
import utils.vision.Block;

public class BlockMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Block[] blocks = new Block[3];

			BlockRobot robot = new BlockRobot();
						
			System.out.println("Input block formation (A = RED, B = YELLOW, BLACK = BLUE):");
			
			for (int i = 0; i < 3; i++) {
				
				System.out.print("Block " + i + ":");
				
				while(true){
					if (new AButton().isPushed()) {
						blocks[i] = Block.RED;
						while(new AButton().isPushed());
						System.out.println("RED");
						break;
					} else if (new BButton().isPushed()) {
						blocks[i] = Block.YELLOW;
						while(new BButton().isPushed());
						System.out.println("YELLOW");
						break;
					} else if (new BlackButton().isPushed()){
						blocks[i] = Block.BLUE;
						while(new BlackButton().isPushed());
						System.out.println("BLUE");
						break;
					}
				}
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		

			
			new TaskRunner(robot, BlockTaskChain.openingMoves()).run();
			
			if (Block.getBlock(0) == Block.RED)
				new TaskRunner(robot, BlockTaskChain.grabFirstBlock()).run();
			
			new TaskRunner(robot, BlockTaskChain.getBlockOrder()).run();
			
			new TaskRunner(robot, BlockTaskChain.getBlockGatherChain(blocks, blocks[0] == Block.RED ? 1 : 0)).run();
			
		} catch (TaskException e){
			e.printStackTrace();
		} catch (CreateConnectException e){
			e.printStackTrace();
		}
		
	}

}
