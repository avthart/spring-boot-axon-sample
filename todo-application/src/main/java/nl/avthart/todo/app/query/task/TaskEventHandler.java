package nl.avthart.todo.app.query.task;

import nl.avthart.todo.app.domain.task.events.TaskCompletedEvent;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TitleModifiedEvent;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskEventHandler {

	@Autowired
	private TaskQueryRepository taskQueryRepository;
	
	@EventHandler
	void on(TaskCreatedEvent event) {
		TaskEntry task = new TaskEntry();
		task.setIdentifier(event.getIdentifier());
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
		task.setTitle(event.getIdentifier());
		
		taskQueryRepository.save(task);
	}
}
