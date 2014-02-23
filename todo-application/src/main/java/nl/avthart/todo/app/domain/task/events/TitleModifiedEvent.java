package nl.avthart.todo.app.domain.task.events;

public class TitleModifiedEvent {

	private final String identifier;
	
	private final String title;

	public TitleModifiedEvent(String identifier, String title) {
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
