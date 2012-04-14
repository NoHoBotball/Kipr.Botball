import java.util.List;

import regionals2012.TaskRunner;

import utils.Constants.Block;
import utils.pathfinding.Task;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Block[] blocks = new Block[] {Block.BLUE, Block.RED, Block.YELLOW};
		List<Task> blockChain = Task.getTaskChain(blocks, false);
		
		TaskRunner blockTask = new TaskRunner(blockChain);
		blockTask.run();
		
	}

}
