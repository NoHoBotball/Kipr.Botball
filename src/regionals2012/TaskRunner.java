package regionals2012;

import java.util.List;


import robot.LegoRobot;
import robot.BlockRobot;
import robot.Robot;
import robot.BlockRobot;
import robot.KelpRobot;
import robot.extentions.AdjustBlockRobot;
import robot.extentions.GetBlockRobot;
import robot.extentions.GrabRobot;

import utils.tasks.AdjustBlockTask;
import utils.tasks.DriveTask;
import utils.tasks.ETDriveTask;
import utils.tasks.GetBlockTask;
import utils.tasks.GrabTask;
import utils.tasks.KelpTask;
import utils.tasks.ListTask;
import utils.tasks.ReleaseTask;
import utils.tasks.Task;
import utils.tasks.TurnTask;
import utils.Conversions;
import utils.KelpConstants;
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
				robot.getDriveTrain().moveCm((Conversions.inToCm(driveTask.getDistance())/1.3) - 2, STANDARD_KELP_SPEED);
				if(driveTask.getDistance() > 0 )
					robot.getDriveTrain().moveCm(2, STANDARD_KELP_SPEED/2);
				else if(driveTask.getDistance() < 0)
					robot.getDriveTrain().moveCm(2, STANDARD_KELP_SPEED/2);
				//robot.getDriveTrain().moveCm(KelpRobot.calibratedValue(KelpConstants.calibrator,Conversions.inToCm( driveTask.getDistance())), driveTask.getSpeed());
			}

			else if (task instanceof ETDriveTask){
				ETDriveTask ETDriveTask = (ETDriveTask) task;
				robot.getDriveTrain().moveAtCmps(ETDriveTask.getSpeed());
				while(KelpRobot.getETSensorValue() < ETDriveTask.getETValue()){
					System.out.println(KelpRobot.getETSensorValue() + " " + ETDriveTask.getETValue());
				}
				if(ETDriveTask.getSpeed() > 0)
					robot.getDriveTrain().moveCm(5,3);
				robot.getDriveTrain().kill();
			} 

			else if (task instanceof TurnTask) {
				TurnTask turnTask = (TurnTask) task;
				if (turnTask.isArcTurn() == false)
					robot.getDriveTrain().rotateDegrees(turnTask.getAngle(), turnTask.getSpeed());
				else if(turnTask.isArcTurn() == true)
					robot.getDriveTrain().moveCurveDegrees(turnTask.getAngle(), turnTask.getRadius(), turnTask.getSpeed());
					//double distanceToTravel =  (KelpConstants.ROBOT_TURN_CIRCUMFERENCE*(turnTask.getAngle()/360))/1.3;
					//robot.getDriveTrain().rotateDegrees((distanceToTravel/KelpConstants.ROBOT_TURN_CIRCUMFERENCE)*360, turnTask.getSpeed());
			} 

			else if (task instanceof GrabTask && robot instanceof GrabRobot) {
				System.out.println("Claw, go!");
				((GrabRobot)robot).grab();
			} 

			else if (task instanceof ReleaseTask && robot instanceof GrabRobot){
				((GrabRobot)robot).release();
			} 

			else if (task instanceof GetBlockTask && robot instanceof GetBlockRobot) {
				GetBlockTask getBlockTask = (GetBlockTask) task;
				Block.setBlock(getBlockTask.getLocation(), getBlockTask.getBlock());
			} 

			else if (task instanceof AdjustBlockTask && robot instanceof BlockRobot) {
				((AdjustBlockRobot)robot).adjustBlock();
			} 

			else if (task instanceof ListTask) {
				ListTask tList = (ListTask)task;
				new TaskRunner(robot, tList.getTaskChain()).run();
			}
		}
	}
}


