package nl.avthart.todo.app.domain.task.events;

/**
 * @author albert
 */
public class TaskCompletedEvent {

	private final String identifier;
	
	public TaskCompletedEvent(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
