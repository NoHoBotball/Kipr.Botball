package utils.tasks;

public class WaitTask extends Task {

	private long millisWaitTime;
	public WaitTask(double waitTime) {
		this.millisWaitTime = (long)(waitTime*1e-3);
	}
	
	public WaitTask(long millisWaitTime) {
		this.millisWaitTime = millisWaitTime;
	}
	
	public long getMillisWaitTime() {
		return millisWaitTime;
	}
}
