import java.util.List;

import regionals2012.KelpTaskChain;
import regionals2012.TaskRunner;
import robot.KelpRobot;

import utils.tasks.TaskException;

public class KelpMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {




		try {

			KelpRobot robot = new KelpRobot();

			TaskRunner toFirstKelpChain = new TaskRunner(robot, KelpTaskChain.moveToKelpChain(1));
			toFirstKelpChain.run();

			TaskRunner getKelpChain = new TaskRunner(robot, KelpTaskChain.getKelpChain());
			getKelpChain.run();

			TaskRunner returnKelpChain = new TaskRunner(robot, KelpTaskChain.returnKelpChain());
			returnKelpChain.run();
			
			TaskRunner toSecondKelpChain = new TaskRunner(robot, KelpTaskChain.moveToKelpChain(2));
			toSecondKelpChain.run();

			getKelpChain.run();

			//TaskRunner returnKelpChain = new TaskRunner(robot, KelpTaskChain.returnKelpChain());
			returnKelpChain.run(); //May have to make two return chains

		} catch (TaskException e){
			e.printStackTrace();
		} 

	}

}
