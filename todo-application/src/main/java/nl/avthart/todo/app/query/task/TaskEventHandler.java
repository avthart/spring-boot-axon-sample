package nl.avthart.todo.app.query.task;

import nl.avthart.todo.app.domain.task.events.TaskCompletedEvent;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TaskStarredEvent;
import nl.avthart.todo.app.domain.task.events.TaskUnstarredEvent;
import nl.avthart.todo.app.domain.task.events.TitleModifiedEvent;
import nl.avthart.todo.app.query.task.TaskQueryEvent.Type;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

/**
 * @author albert
 */
@Component
public class TaskEventHandler {

	private final TaskQueryRepository taskQueryRepository;
	
	private final SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	public TaskEventHandler(SimpMessageSendingOperations messagingTemplate, TaskQueryRepository taskQueryRepository) {
		this.messagingTemplate = messagingTemplate;
		this.taskQueryRepository = taskQueryRepository;
	}
	
	@EventHandler
	void on(TaskCreatedEvent event) {
		TaskEntry task = new TaskEntry();
		task.setId(event.getIdentifier());
		task.setUsername(event.getUsername());
		task.setTitle(event.getTitle());		
		
		taskQueryRepository.save(task);

		publish(Type.ADDED, task);
	}

	@EventHandler
	void on(TaskCompletedEvent event) {
		TaskEntry task = taskQueryRepository.findOne(event.getIdentifier());
		task.setCompleted(true);
		
		taskQueryRepository.save(task);
		
		publish(Type.COMPLETED, task);
	}
	
	@EventHandler
	void on(TitleModifiedEvent event) {
		TaskEntry task = taskQueryRepository.findOne(event.getIdentifier());
		task.setTitle(event.getTitle());
		
		taskQueryRepository.save(task);
		
		publish(Type.MODIFIED, task);
	}
	
	@EventHandler
	void on (TaskStarredEvent event) {
		TaskEntry task = taskQueryRepository.findOne(event.getIdentifier());
		task.setStarred(true);
		
		taskQueryRepository.save(task);
		
		publish(Type.MODIFIED, task);
	}
	
	@EventHandler
	void on (TaskUnstarredEvent event) {
		TaskEntry task = taskQueryRepository.findOne(event.getIdentifier());
		task.setStarred(false);
		
		taskQueryRepository.save(task);
		
		publish(Type.MODIFIED, task);
	}
	
	private void publish(Type type, TaskEntry task) {
		this.messagingTemplate.convertAndSendToUser(task.getUsername(), "/queue/task-updates", new TaskQueryEvent(type, task));
	}
}
