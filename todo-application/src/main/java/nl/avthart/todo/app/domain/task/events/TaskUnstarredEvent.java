package nl.avthart.todo.app.domain.task.events;

/**
 * @author albert
 */
public class TaskUnstarredEvent {

	private final String identifier;
	
	public TaskUnstarredEvent(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
