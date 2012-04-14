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
		
		for (int i = 0; i < 3; i++) {
			
			System.out.println("Input block " + i + " (A = RED, B = YELLOW, BLACK = BLUE)");
			boolean a = false, b = false;
			while ((a = new AButton().isNotPushed()) && (b = new BButton().isNotPushed()) && (new BlackButton().isNotPushed()));
			if (a)
				blocks[i] = Block.RED;
			else if (b)
				blocks[i] = Block.YELLOW;
			else
				blocks[i] = Block.BLUE;
			
		}
		
		List<Task> blockChain = Task.getTaskChain(blocks, blocks[0] == Block.RED);
		
		TaskRunner blockTask = new TaskRunner(blockChain);
		blockTask.run();
		
	}

}
