package nl.avthart.todo.app.domain.task;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import nl.avthart.todo.app.domain.task.commands.CompleteTaskCommand;
import nl.avthart.todo.app.domain.task.commands.CreateTaskCommand;
import nl.avthart.todo.app.domain.task.commands.ModifyTitleCommand;
import nl.avthart.todo.app.domain.task.commands.StarTaskCommand;
import nl.avthart.todo.app.domain.task.commands.UnstarTaskCommand;
import nl.avthart.todo.app.domain.task.events.TaskCompletedEvent;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TaskStarredEvent;
import nl.avthart.todo.app.domain.task.events.TaskUnstarredEvent;
import nl.avthart.todo.app.domain.task.events.TitleModifiedEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Task
 * @author albert
 */
@Entity
public class Task extends AbstractAnnotatedAggregateRoot<String> {

	/**
	 * The constant serialVersionUID 
	 */
	private static final long serialVersionUID = -5977984483620451665L;
	
	@AggregateIdentifier
	@Id
	private String id;
	
	@NotNull
	private boolean completed;
	
	/**
	 * Creates a new Task.
	 * 
	 * @param command create Task
	 */
	@CommandHandler
	public Task(CreateTaskCommand command) {
		apply(new TaskCreatedEvent(command.getIdentifier(), command.getTitle()));
	}
	
	Task() {
	}

	/**
	 * Completes a Task.
	 * 
	 * @param command complete Task
	 */
	@CommandHandler
	void on(CompleteTaskCommand command) {
		apply(new TaskCompletedEvent(command.getIdentifier()));
	}
	
	/**
	 * Stars a Task.
	 * 
	 * @param command star Task
	 */
	@CommandHandler
	void on(StarTaskCommand command) {
		apply(new TaskStarredEvent(command.getIdentifier()));
	}
	
	/**
	 * Unstars a Task.
	 * 
	 * @param command unstar Task
	 */
	@CommandHandler
	void on(UnstarTaskCommand command) {
		apply(new TaskUnstarredEvent(command.getIdentifier()));
	}
	
	/**
	 * Modifies a Task title.
	 * 
	 * @param command modify Task title
	 */
	@CommandHandler
	void on(ModifyTitleCommand command) {
		assertNotCompleted();
		apply(new TitleModifiedEvent(command.getIdentifier(), command.getTitle()));
	}
	
	@EventHandler
	void on(TaskCreatedEvent event) {
		this.id = event.getIdentifier();
	}
	
	@EventHandler
	void on(TaskCompletedEvent event) {
		this.completed = true;
	}
	
	private void assertNotCompleted() {
		if (completed) {
			throw new TaskAlreadyCompletedException("Task [ identifier = " + id + " ] is completed.");
		}		
	}
}
