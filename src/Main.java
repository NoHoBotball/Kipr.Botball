import java.util.List;

import cbccore.sensors.buttons.AButton;
import cbccore.sensors.buttons.BButton;
import cbccore.sensors.buttons.BlackButton;

import regionals2012.TaskRunner;

import utils.Constants.Block;
import utils.pathfinding.Task;

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
		
		List<Task> blockChain = Task.getTaskChain(blocks, blocks[0] == Block.RED);
		
		TaskRunner blockTask = new TaskRunner(blockChain);
		blockTask.run();
		
	}

}
