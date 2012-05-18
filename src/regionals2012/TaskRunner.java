package regionals2012;

import java.util.List;

import robot.KelpRobot;
import robot.Robot;
import robot.extentions.AdjustBlockRobot;
import robot.extentions.GetBlockRobot;
import robot.extentions.GrabRobot;
import robot.extentions.StackBlockRobot;
import utils.Conversions;
import utils.KelpConstants;
import utils.tasks.AdjustBlockTask;
import utils.tasks.DriveTask;
import utils.tasks.ETDriveTask;
import utils.tasks.GrabTask;
import utils.tasks.ListTask;
import utils.tasks.ReleaseTask;
import utils.tasks.SeeBlockTask;
import utils.tasks.StackBlockTask;
import utils.tasks.Task;
import utils.tasks.TurnTask;
import utils.vision.Block;

public class TaskRunner implements Runnable, KelpConstants {

	private Robot robot;
	private List<Task> taskChain;

	public TaskRunner (Robot robot, List<Task> taskChain) {
		this.taskChain = taskChain;	
		this.robot = robot; 
	}

	@Override
	public void run() {
		for (Task task : taskChain) {
			// Handle task type
			if (task instanceof DriveTask) {
				DriveTask driveTask = (DriveTask) task;
				robot.getDriveTrain().moveCm(Conversions.inToCm(driveTask.getDistance()), driveTask.getSpeed());
			} else if (task instanceof ETDriveTask){
				ETDriveTask ETDriveTask = (ETDriveTask) task;
				robot.getDriveTrain().moveAtCmps(ETDriveTask.getSpeed());
				while(KelpRobot.getETSensorValue() < ETDriveTask.getETValue()){}
				robot.getDriveTrain().kill();
			} else if (task instanceof TurnTask) {
				TurnTask turnTask = (TurnTask) task;
				robot.getDriveTrain().rotateDegrees(turnTask.getAngle(), turnTask.getSpeed());
				System.out.println("Turning " + turnTask.getAngle() + 
						           "deg at " + turnTask.getSpeed() + " deg/sec");
			}else if (task instanceof GrabTask && robot instanceof GrabRobot) {
				System.out.println("Claw, go!");
				((GrabRobot)robot).grab();
			} else if (task instanceof ReleaseTask && robot instanceof GrabRobot){
				((GrabRobot)robot).release();
			} else if (task instanceof SeeBlockTask && robot instanceof GetBlockRobot) {
				SeeBlockTask getBlockTask = (SeeBlockTask) task;
				Block.setBlock(getBlockTask.getLocation(), getBlockTask.getBlock());
			} else if (task instanceof AdjustBlockTask && robot instanceof AdjustBlockRobot) {
				((AdjustBlockRobot)robot).adjustBlock();
			} else if (task instanceof StackBlockTask && robot instanceof StackBlockRobot) {
				((StackBlockRobot)robot).stack();
			} else if (task instanceof ListTask) {
				ListTask tList = (ListTask)task;
				new TaskRunner(robot, tList.getTaskChain()).run();
			}
		}
	}
}


