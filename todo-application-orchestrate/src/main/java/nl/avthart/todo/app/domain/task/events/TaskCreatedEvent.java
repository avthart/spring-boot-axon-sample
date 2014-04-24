package nl.avthart.todo.app.domain.task.events;

import lombok.Value;

/**
 * @author albert
 */
@Value
public class TaskCreatedEvent implements TaskEvent {

	private final String id;
	
	private final String username;
	
	private final String title;	
}
