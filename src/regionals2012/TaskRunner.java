package regionals2012;

import java.util.List;

import cbccore.create.Create;
import cbccore.create.CreateConnectException;
import cbccore.movement.DriveTrain;
import cbccore.movement.plugins.create.CreateMovementPlugin;

import utils.Constants;
import utils.pathfinding.DriveTask;
import utils.pathfinding.GrabTask;
import utils.pathfinding.Task;
import utils.pathfinding.TurnTask;

public class TaskRunner implements Runnable {
	
	private DriveTrain driveTrain;
	
	private List<Task> taskChain;
	
	public TaskRunner (List<Task> taskChain) {
		this.taskChain = taskChain;
		
		try {
			driveTrain = new DriveTrain(new CreateMovementPlugin(new Create()));
		} catch (CreateConnectException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		// Loop through all tasks in chain
		for (Task task : taskChain) {
			
			// Handle task type
			if (task instanceof DriveTask) {
				DriveTask driveTask = (DriveTask) task;
				driveTrain.moveCm(Constants.inchesToCentimeters(driveTask.getDistance()), driveTask.getSpeed());
			} else if (task instanceof TurnTask) {
				TurnTask turnTask = (TurnTask) task;
				driveTrain.rotateDegrees(turnTask.getAngle(), turnTask.getSpeed());
			} else if (task instanceof GrabTask) {
				// TODO: Implement claw motion
			}
			
		}
		
	}
	
}
