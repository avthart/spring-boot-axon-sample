package nl.avthart.todo.app.domain.task.events;

/**
 * @author albert
 */
public class TaskCreatedEvent {

	private final String identifier;
	
	private final String username;
	
	private final String title;	

	public TaskCreatedEvent(String identifier, String username, String title) {
		this.identifier = identifier;
		this.username = username;
		this.title = title;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getUsername() {
		return username;
	}
}
