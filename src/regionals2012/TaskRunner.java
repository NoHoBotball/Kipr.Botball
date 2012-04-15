package regionals2012;

import java.util.List;

import robot.Robot;

import cbccore.movement.DriveTrain;

import utils.Constants;
import utils.pathfinding.DriveTask;
import utils.pathfinding.GrabTask;
import utils.pathfinding.Task;
import utils.pathfinding.TurnTask;

public class TaskRunner implements Runnable {
	
	private DriveTrain driveTrain;
	
	private List<Task> taskChain;
	
	public TaskRunner (Robot robot, List<Task> taskChain) {
		this.taskChain = taskChain;	
		this.driveTrain = robot.getDriveTrain();	
	}
	
	@Override
	public void run() {
		
		// Loop through all tasks in chain
		for (Task task : taskChain) {
			
			// Handle task type
			if (task instanceof DriveTask) {
				DriveTask driveTask = (DriveTask) task;
				driveTrain.moveCm(Constants.inchesToCentimeters(driveTask.getDistance()), -driveTask.getSpeed());
			} else if (task instanceof TurnTask) {
				TurnTask turnTask = (TurnTask) task;
				driveTrain.rotateDegrees(turnTask.getAngle(), turnTask.getSpeed());
			} else if (task instanceof GrabTask) {
				System.out.println("Claw, go!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// TODO: Implement claw motion
				System.out.println("Grabbed.");
			}
			
		}
		
	}
	
}
