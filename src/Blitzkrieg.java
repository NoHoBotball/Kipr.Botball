import java.util.ArrayList;
import java.util.List;

import cbc.create.CreateConnectException;
import cbc.movement.DriveTrain;
import cbc.movement.plugins.create.CreateMovementPlugin;
import cbc.movement.plugins.motor.MotorMovementPlugin;
import cbc.movement.plugins.motor.Wheel;
import cbc.create.Create;
import regionals2012.TaskRunner;
import robot.BlitzRobot;
import robot.Robot;
import utils.BlitzConstants;
import utils.pathfinding.Direction;
import utils.pathfinding.Location;
import utils.tasks.AdjustBlockTask;
import utils.tasks.DriveTask;
import utils.tasks.ETDriveTask;
import utils.tasks.GrabTask;
import utils.tasks.ListTask;
import utils.tasks.SeeBlockTask;
import utils.tasks.StackBlockTask;
import utils.tasks.Task;
import utils.tasks.TaskException;
import utils.tasks.TurnTask;


public class Blitzkrieg implements BlitzConstants {
	protected static Direction heading = Direction.NORTH;
	static boolean createBot = false;
	private static List<Task> tasks = new ArrayList<Task>();
	private static List<Task> _tasks;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DriveTrain dt;
			init();
			if(createBot == true) {
				dt = new DriveTrain(
						new CreateMovementPlugin(
							new Create()));
			} else {
				dt = new DriveTrain(
						new MotorMovementPlugin(
							new Wheel(LEFT_WHEEL_PORT, WHEEL_CIRCUMFERENCE),
							new Wheel(RIGHT_WHEEL_PORT, WHEEL_CIRCUMFERENCE), DT_WIDTH));
			}
			BlitzRobot blitz = new BlitzRobot(dt);
			turn(Direction.SOUTH,15.);
			dt.moveCm(OUT_OF_STARTING_BOX, BLITZ_SPEED);
			turn(Direction.EAST);
			dt.moveCm(TO_OTHER_SIDE, BLITZ_SPEED);
			turn(Direction.NORTH);
			dt.moveCm(-TO_OTHER_STARTING_BOX, BLITZ_SPEED);
			
			new TaskRunner(blitz, tasks).run();
		} catch (CreateConnectException e) {
			e.printStackTrace();
		} catch (TaskException e) {
			e.printStackTrace();
		}
	}
	
	public static void init() {
		
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
	
	private static void turn(Direction heading) throws TaskException {
		add(new TurnTask(Blitzkrieg.heading, heading));
		Blitzkrieg.heading = heading;
	}
	
	private static void turn(Direction heading, double speed) throws TaskException {
		add(new TurnTask(heading, heading, speed));
		Blitzkrieg.heading = heading;
	}
	private static void turn(double degrees) throws TaskException {
		add(new TurnTask(degrees));
	}
	
	private static void turn(double degrees, double speed) throws TaskException {
		add(new TurnTask(degrees , speed));
	}
	private static void stack() {
		add(StackBlockTask.getTask());
	}

}
