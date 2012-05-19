
import cbc.sensors.buttons.BlackButton;

import regionals2012.KelpTaskChain;
import regionals2012.TaskRunner;
import robot.KelpRobot;

import utils.tasks.TaskException;

  
public class KelpMain {  

	/** 
	 * @param args      
	 * @throws TaskException    
	 */        
	public static void main(String[] args) throws TaskException {
 
		BlackButton blackButton = new BlackButton(); 
		KelpRobot robot = new KelpRobot();
 
		robot.getArm().goToPos(2); 
		     
		while(!blackButton.getValue()){}
		   
		robot.getDriveTrain().moveCm(25,4);
		robot.getDriveTrain().moveCm(-13, 4);
		robot.getETSensor().setFloating(true);
		
		while(!blackButton.getValue()){}

		System.out.println("Starting Tasks."); 

		try {

			TaskRunner toFirstKelpChain = new TaskRunner(robot, KelpTaskChain.moveToKelpChain(1));
			toFirstKelpChain.run();

			TaskRunner getFirstKelpChain = new TaskRunner(robot, KelpTaskChain.getKelpChain());
			getFirstKelpChain.run();

			TaskRunner returnFirstKelpChain = new TaskRunner(robot, KelpTaskChain.returnKelpChain(1));
			returnFirstKelpChain.run();

   
 

			TaskRunner toSecondKelpChain = new TaskRunner(robot, KelpTaskChain.moveToKelpChain(2));
			toSecondKelpChain.run();

			TaskRunner getSecondKelpChain = new TaskRunner(robot, KelpTaskChain.getKelpChain());
			getSecondKelpChain.run();

			TaskRunner returnSecondKelpChain = new TaskRunner(robot, KelpTaskChain.returnKelpChain(2));
			returnSecondKelpChain.run(); 

		} catch (TaskException e){
			e.printStackTrace();
		} 
	}
}

