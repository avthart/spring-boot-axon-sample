package nl.avthart.todo.app.domain.task.events;

/**
 * @author albert
 */
public class TaskStarredEvent {

	private final String identifier;
	
	public TaskStarredEvent(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
