package nl.avthart.todo.app.domain.task.events;

public class TaskCompletedEvent {

	private final String identifier;
	
	public TaskCompletedEvent(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
