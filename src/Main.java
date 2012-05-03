import java.util.List;

import cbccore.create.CreateConnectException;
import cbccore.sensors.buttons.AButton;
import cbccore.sensors.buttons.BButton;
import cbccore.sensors.buttons.BlackButton;

import regionals2012.BlockTaskChain;
import regionals2012.TaskRunner;
import robot.BlockRobot;

import utils.vision.Block;
import utils.pathfinding.TaskException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Block[] blocks = new Block[3];
		
		System.out.println("Input block formation (A = RED, B = YELLOW, BLACK = BLUE):");
		
		for (int i = 0; i < 3; i++) {
			
			System.out.print("Block " + i + ":");
			
			boolean a = false, b = false;
			while ((a = new AButton().isNotPushed()) && (b = new BButton().isNotPushed()) && (new BlackButton().isNotPushed()));
			if (a) {
				blocks[i] = Block.RED;
				while(new AButton().isPushed());
				System.out.println("RED");
			} else if (b) {
				blocks[i] = Block.YELLOW;
				while(new BButton().isPushed());
				System.out.println("YELLOW");
			} else {
				blocks[i] = Block.BLUE;
				while(new BlackButton().isPushed());
				System.out.println("BLUE");
			}
			
		}
		
		
		try {
			
			BlockRobot robot = new BlockRobot();
			
			TaskRunner startingChain = new TaskRunner(robot, BlockTaskChain.getOpeningMovesChain());
			startingChain.run();
			
			TaskRunner colorChain = new TaskRunner(robot, BlockTaskChain.getBlockColorChain(Block.getBlock(0) == Block.RED));
			colorChain.run();
			
			TaskRunner gatherChain = new TaskRunner(robot, BlockTaskChain.getBlockGatherChain(blocks, blocks[0] == Block.RED ? 1 : 0));
			gatherChain.run();
			
		} catch (TaskException e){
			e.printStackTrace();
		} catch (CreateConnectException e){
			e.printStackTrace();
		}
		
	}

}
