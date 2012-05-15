package utils.tasks;

import java.util.List;

public class ListTask extends Task {
	
	private List<Task> taskChain;
	
	public ListTask(List<Task> taskChain) {
		this.taskChain = taskChain;
	}
	
	public List<Task> getTaskChain() {
		return taskChain;
	}
	
}
