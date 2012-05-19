package regionals2012;

import java.util.List;

import robot.KelpRobot;
import robot.Robot;
import robot.extentions.AdjustBlockRobot;
import robot.extentions.ArmRobot;
import robot.extentions.ClawRobot;
import robot.extentions.ETRobot;
import robot.extentions.GetBlockRobot;
import robot.extentions.GrabRobot;
import robot.extentions.StackBlockRobot;
import utils.Conversions;
import utils.KelpConstants;
import utils.tasks.AdjustBlockTask;
import utils.tasks.ArmTask;
import utils.tasks.DriveTask;
import utils.tasks.ETDriveTask;
import utils.tasks.SlowDriveTask;
import utils.tasks.GrabTask;
import utils.tasks.ListTask;
import utils.tasks.ReleaseTask;
import utils.tasks.SeeBlockTask;
import utils.tasks.StackBlockTask;
import utils.tasks.Task;
import utils.tasks.TurnTask;
import utils.tasks.WaitTask;
import utils.Conversions;
import utils.KelpConstants;
import utils.vision.Block;

public class TaskRunner implements Runnable, KelpConstants {

	private Robot robot;
	private List<Task> taskChain;
	private boolean kelpDone = false;

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
			} else if (task instanceof SlowDriveTask){
				SlowDriveTask slowDriveTask = (SlowDriveTask) task;
				if(slowDriveTask.getDistance() > 0){
					robot.getDriveTrain().moveCm(Conversions.inToCm(slowDriveTask.getDistance() - slowDriveTask.getSlowDistance()), slowDriveTask.getSpeed());
					robot.getDriveTrain().moveCm(Conversions.inToCm(slowDriveTask.getSlowDistance()), slowDriveTask.getSpeed()/2); 
				} else if (slowDriveTask.getDistance() < 0){
					robot.getDriveTrain().moveCm(Conversions.inToCm(slowDriveTask.getDistance() + slowDriveTask.getSlowDistance()), slowDriveTask.getSpeed());
					robot.getDriveTrain().moveCm(Conversions.inToCm(-slowDriveTask.getSlowDistance()), slowDriveTask.getSpeed()/2);
				}
			} else if (task instanceof ETDriveTask && robot instanceof ETRobot){
				ETDriveTask etDriveTask = (ETDriveTask) task;
				robot.getDriveTrain().moveAtCmps(etDriveTask.getSpeed());
				//	ETRobot jRobot = (ETRobot) robot;
				System.out.println("" + ((ETRobot)robot).getETSensor().getValueHigh() + " " + etDriveTask.getETValue());
				while(((ETRobot)robot).getETSensor().getValueHigh() < etDriveTask.getETValue()){
					System.out.println("" + ((ETRobot)robot).getETSensor().getValueHigh() + " " + etDriveTask.getETValue());
				}
				robot.getDriveTrain().kill();
			} else if (task instanceof TurnTask) {
				TurnTask turnTask = (TurnTask) task;
				robot.getDriveTrain().rotateDegrees(turnTask.getAngle(), turnTask.getSpeed());
				System.out.println("Turning " + turnTask.getAngle() + 
						"deg at " + turnTask.getSpeed() + " deg/sec");
			} else if (task instanceof GrabTask && robot instanceof GrabRobot) {
				System.out.println("Claw, go!");
				((GrabRobot)robot).grab();
			} else if (task instanceof ReleaseTask && robot instanceof GrabRobot){
				((GrabRobot)robot).release();
			} else if (task instanceof SeeBlockTask && robot instanceof GetBlockRobot) {
				SeeBlockTask getBlockTask = (SeeBlockTask) task;
			} else if (task instanceof ArmTask && robot instanceof ArmRobot && robot instanceof ClawRobot){
				if(kelpDone  == false){
					((ClawRobot)robot).getClaw().quarterOpen();
					((ArmRobot)robot).getArm().goToPos(0);
				} else if(kelpDone == true){
					((ClawRobot)robot).getClaw().close();
					((ArmRobot)robot).getArm().goToPos(2);
				}
				kelpDone = true;
			} else if (task instanceof SeeBlockTask && robot instanceof GetBlockRobot) {
				SeeBlockTask getBlockTask = (SeeBlockTask) task;
				Block.setBlock(getBlockTask.getLocation(), getBlockTask.getBlock());
			} else if (task instanceof AdjustBlockTask && robot instanceof AdjustBlockRobot) {
				((AdjustBlockRobot)robot).adjustBlock();
			} else if (task instanceof StackBlockTask && robot instanceof StackBlockRobot) {
				((StackBlockRobot)robot).stack();
			} else if (task instanceof WaitTask) {
				try {
					Thread.sleep(((WaitTask)task).getMillisWaitTime());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (task instanceof ListTask) {
				ListTask tList = (ListTask)task;
				new TaskRunner(robot, tList.getTaskChain()).run();
			}

		}
	}
}


