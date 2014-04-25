package nl.avthart.todo.app.event.task;

import io.orchestrate.client.Client;
import nl.avthart.todo.app.domain.task.events.TaskCompletedEvent;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TaskEvent;
import nl.avthart.todo.app.domain.task.events.TaskStarredEvent;
import nl.avthart.todo.app.domain.task.events.TaskUnstarredEvent;
import nl.avthart.todo.app.domain.task.events.TaskTitleModifiedEvent;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author albert
 */
@Component
public class TaskEventHandler {
	
	private final static String COLLECTION = "tasks";

	private final Client client;
	
	@Autowired
	public TaskEventHandler(Client client) {
		this.client = client;
	}
	
	@EventHandler
	void on(TaskEvent event) {
		client.event(COLLECTION, event.getId()).type("log").put(event);
	}
}
