package robot.extentions;


public interface ClawRobot {
	public Claw getClaw();
	public abstract class Claw {
		public abstract void open();
		public abstract void close();
		public abstract void halfOpen();
		// TODO Auto-generated method stub
		public abstract void quarterOpen();
		// TODO Auto-generated method stub

	}


}

