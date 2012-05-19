package regionals2012;

import java.util.ArrayList;
import java.util.List;

import utils.tasks.ArmTask;
import utils.tasks.SlowDriveTask;
import utils.tasks.ETDriveTask;
import utils.tasks.GrabTask;
import utils.tasks.ReleaseTask;
import utils.tasks.SlowDriveTask;
import utils.tasks.Task;
import utils.tasks.TaskException;
import utils.tasks.TurnTask;
import utils.tasks.WaitTask;
import utils.Conversions;
import utils.KelpConstants;
import utils.pathfinding.Location;
import utils.pathfinding.Direction;


/**
 * Put all tasks chains to be generated throughout the duration
 * of the 2012 Botball Greater LA regional competition in this
 * class as static methods.
 *
 */

public class KelpTaskChain implements KelpConstants {

	public static List<Task> moveToKelpChain(int firstOrSecond) throws TaskException{
		List<Task> tasks = new ArrayList<Task>();

		if (firstOrSecond == 1){
			tasks.add(TurnTask.turnCCW(15));
			tasks.add(new SlowDriveTask(26, STANDARD_KELP_SPEED, 5)); //27
			tasks.add(TurnTask.turnCCW());
			tasks.add(new SlowDriveTask(15, STANDARD_KELP_SPEED)); //drive forward 
			tasks.add(new ArmTask());
			tasks.add(new SlowDriveTask(-8, STANDARD_KELP_SPEED)); //8
			tasks.add(new ArmTask());
			tasks.add(new SlowDriveTask(19, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 950));
			tasks.add(new SlowDriveTask(4,4));
			tasks.add(new SlowDriveTask(-17, STANDARD_KELP_SPEED/2)); //-14
			tasks.add(new TurnTask(100)); 
		} else if (firstOrSecond == 2){
			tasks.add(new SlowDriveTask(-5, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
			tasks.add(new TurnTask(2,-STANDARD_KELP_SPEED)); //.5
			tasks.add(new ETDriveTask(75, 950));
			tasks.add(new SlowDriveTask(4,4));
			tasks.add(new SlowDriveTask(-10, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
		} else {
			System.out.println("ERROR: firstOrSecond must be either 1 or 2.");
		}
		return tasks;
		
		/*In the event that BlockRobot takes too long to accomplish its task, KelpRobot can
		 * drive forward at max speed to MPA PVC. From there, it can turn CCW, get the far Kelp first, then allow 
		 * BlockRobot to complete its task while it gets the close Kelp. */
	}

	public static List<Task> getKelpChain() throws TaskException {
		List<Task> tasks = new ArrayList<Task>();

		tasks.add(GrabTask.getTask());

		return tasks;
	}


	public static List<Task> returnKelpChain(int firstOrSecond) throws TaskException{
		// Initialize task chain
		List<Task> tasks = new ArrayList<Task>();	


		if(firstOrSecond == 1){
			tasks.add(new SlowDriveTask(-8, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
			tasks.add(new TurnTask(-45, -STANDARD_KELP_SPEED));
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 700));
			tasks.add(new TurnTask(-45, -STANDARD_KELP_SPEED));
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 900));
			//tasks.add(new TurnTask(20, STANDARD_KELP_SPEED));
			//tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 960));
			tasks.add(new SlowDriveTask(4,4));
		}
		if(firstOrSecond == 2){
			tasks.add(new SlowDriveTask(-3, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
			tasks.add(new TurnTask(7,-STANDARD_KELP_SPEED)); //2
			tasks.add(new ETDriveTask(75, 900));
			tasks.add(new SlowDriveTask(-4, STANDARD_KELP_SPEED));
			tasks.add(TurnTask.turnCCW());
			tasks.add(new ETDriveTask(STANDARD_KELP_SPEED, 960));
			//tasks.add(new SlowDriveTask(3,4));
		}
		

		tasks.add(ReleaseTask.getTask());
		
		return tasks;
	}

}
