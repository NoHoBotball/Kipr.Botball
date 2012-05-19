

import java.util.ArrayList;
import java.util.List;

import cbc.create.CreateConnectException;
import cbc.motors.Motor;
import cbc.motors.Servo;
import cbc.movement.DriveTrain;
import cbc.sensors.analog.Analog;
import cbc.sensors.buttons.DownButton;
import cbc.sensors.buttons.LeftButton;
import cbc.sensors.buttons.AButton;
import cbc.sensors.buttons.BButton;
import cbc.sensors.buttons.BlackButton;
import cbc.sensors.buttons.RightButton;
import cbc.sensors.buttons.UpButton;

import regionals2012.TaskRunner;
import robot.BlockRobot;
import utils.BlockConstants;
import utils.pathfinding.Direction;
import utils.pathfinding.Location;
import utils.tasks.*;

public class BlockDrive implements BlockConstants {
	
	private static List<Task> tasks;
	private static int blocksGrabbed = 0;
	private static Location location;
	private static Direction heading;
	
	public static void main (String[] args){
		try {
			BlockRobot robot = new BlockRobot();
			tasks = new ArrayList<Task>();
			DriveTrain dt = robot.getDriveTrain();

			while(new BlackButton().isNotPushed()){
				heading = Direction.NORTH;
				tasks = new ArrayList<Task>();
				if (new DownButton().isPushed()){
					robot.getArm().lower();
				} else if (new UpButton().isPushed()){
					robot.getArm().raise();
				} else if(new LeftButton().isPushed()){
					turn(90,15);
				} else if (new RightButton().isPushed()){
					turn(-90);
				} else if (new AButton().isPushed()){
					robot.grab();
				} else if (new BButton().isPushed()){
					robot.release();
				} else {
					//Servo.allOff();
					//Motor.allOff();
					//dt.stop();
				}
				new TaskRunner(robot, tasks).run();
			}
		} catch (CreateConnectException e) {
			e.printStackTrace();
		} catch (TaskException e) {
			e.printStackTrace();
		}
		Servo.allOff();
		Motor.allOff();
	}
	
	private static void add(Task task) {
		tasks.add(task);
	}
	
	private static void drive(double distance, double speed) {
		add(new DriveTask(distance, speed));
	}
	
	private static void drive(double distance) throws TaskException {
		add(new DriveTask(distance));
	}
	
	private static void driveET(double speed, int ET_VALUE) {
		add(new ETDriveTask(speed, ET_VALUE));
	}
	
	private static void grab() {
		add(GrabTask.getTask());
		blocksGrabbed++;
	}
	
	private static void turn(Direction heading) throws TaskException {
		add(new TurnTask(BlockDrive.heading, heading));
		BlockDrive.heading = heading;
	}
	
	private static void turn(Direction heading, double speed) throws TaskException {
		add(new TurnTask(BlockDrive.heading, heading, speed));
		BlockDrive.heading = heading;
	}
	
	private static void turn(double degrees) throws TaskException {
		add(new TurnTask(degrees));
	}
	
	private static void turn(double degrees, double speed) throws TaskException {
		add(new TurnTask(degrees, speed));
	}
	
	private static void adjust(Location location) {
		add(new AdjustBlockTask(location));
	}
	
	private static void adjust() {
		add(new AdjustBlockTask(location));
	}
	
	private static void seeBlock(Location location) {
		add(new SeeBlockTask(location));
	}
	
	private static void seeBlock() {
		add(new SeeBlockTask(location));
	}
	
	private static void stack() {
		add(StackBlockTask.getTask());
	}
	
}
