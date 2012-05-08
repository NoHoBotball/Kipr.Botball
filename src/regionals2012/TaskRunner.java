package regionals2012;

import java.util.List;

import robot.AdjustBlockRobot;
import robot.BlockRobot;
import robot.Robot;
import robot.extentions.GrabRobot;

import utils.Constants;
import utils.pathfinding.AdjustBlockTask;
import utils.pathfinding.GetBlockTask;
import utils.pathfinding.DriveTask;
import utils.pathfinding.GrabTask;
import utils.pathfinding.ListTask;
import utils.pathfinding.Task;
import utils.pathfinding.TurnTask;
import utils.vision.Block;

public class TaskRunner implements Runnable {
	
	private Robot robot;
	private List<Task> taskChain;
	
	public TaskRunner (Robot robot, List<Task> taskChain) {
		this.taskChain = taskChain;	
	}
	
	@Override
	public void run() {
		
		// Loop through all tasks in chain
		for (Task task : taskChain) {
			
			// Handle task type
			if (task instanceof DriveTask) {
				DriveTask driveTask = (DriveTask) task;
				robot.getDriveTrain().moveCm(Constants.inchesToCentimeters(driveTask.getDistance()), -driveTask.getSpeed());
			} else if (task instanceof TurnTask) {
				TurnTask turnTask = (TurnTask) task;
				robot.getDriveTrain().rotateDegrees(turnTask.getAngle(), turnTask.getSpeed());
			} else if (task instanceof GrabTask && robot instanceof GrabRobot) {
				System.out.println("Claw, go!");
				((GrabRobot)robot).grab();
				System.out.println("Grabbed.");
			} else if (task instanceof GetBlockTask && robot instanceof BlockRobot) {
				GetBlockTask blockTask = (GetBlockTask) task;
				Block.setBlock(blockTask.getLocation(), blockTask.getBlock());
			} else if (task instanceof AdjustBlockTask && robot instanceof BlockRobot) {
				AdjustBlockRobot adjRobot = (AdjustBlockRobot)robot;
				adjRobot.adjustBlock();
			} else if (task instanceof ListTask) {
				ListTask tList = (ListTask)task;
				new TaskRunner(robot, tList.getTaskChain()).run();
			}
			
		}
		
	}
	
}
