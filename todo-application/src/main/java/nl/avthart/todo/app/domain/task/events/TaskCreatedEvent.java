package nl.avthart.todo.app.domain.task.events;

public class TaskCreatedEvent {

	private final String identifier;
	
	private final String title;

	public TaskCreatedEvent(String identifier, String title) {
		this.identifier = identifier;
		this.title = title;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getTitle() {
		return title;
	}
}
