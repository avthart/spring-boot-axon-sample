package nl.avthart.todo.app.query.task;

import nl.avthart.todo.app.domain.task.events.TaskCompletedEvent;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TaskStarredEvent;
import nl.avthart.todo.app.domain.task.events.TaskUnstarredEvent;
import nl.avthart.todo.app.domain.task.events.TitleModifiedEvent;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author albert
 */
@Component
public class TaskEventHandler {

	@Autowired
	private TaskQueryRepository taskQueryRepository;
	
	@EventHandler
	void on(TaskCreatedEvent event) {
		TaskEntry task = new TaskEntry();
		task.setId(event.getIdentifier());
		task.setTitle(event.getTitle());
		
		taskQueryRepository.save(task);
	}
	
	@EventHandler
	void on(TaskCompletedEvent event) {
		TaskEntry task = taskQueryRepository.findOne(event.getIdentifier());
		task.setCompleted(true);
		
		taskQueryRepository.save(task);
	}
	
	@EventHandler
	void on(TitleModifiedEvent event) {
		TaskEntry task = taskQueryRepository.findOne(event.getIdentifier());
		task.setTitle(event.getTitle());
		
		taskQueryRepository.save(task);
	}
	
	@EventHandler
	void on (TaskStarredEvent event) {
		TaskEntry task = taskQueryRepository.findOne(event.getIdentifier());
		task.setStarred(true);
		
		taskQueryRepository.save(task);
	}
	
	@EventHandler
	void on (TaskUnstarredEvent event) {
		TaskEntry task = taskQueryRepository.findOne(event.getIdentifier());
		task.setStarred(false);
		
		taskQueryRepository.save(task);
	}
}
