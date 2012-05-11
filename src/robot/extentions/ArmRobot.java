package robot.extentions;


public interface ArmRobot {
	public Arm getArm();
	public abstract class Arm {
		
		int[] Level;
		
		public Arm(int[] Level) {
			this.Level = Level;
		}
		
		public void raise() {
			goToPos(Level[Level.length - 1]);
		}
		
		public void lower() {
			goToPos(Level[0]);
		}
		
		public abstract void goToPos(int pos);

	}
}
