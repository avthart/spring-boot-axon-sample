package nl.avthart.todo.app.domain.task;

import javax.validation.constraints.NotNull;

import nl.avthart.todo.app.domain.task.commands.CompleteTaskCommand;
import nl.avthart.todo.app.domain.task.commands.CreateTaskCommand;
import nl.avthart.todo.app.domain.task.commands.ModifyTaskTitleCommand;
import nl.avthart.todo.app.domain.task.commands.StarTaskCommand;
import nl.avthart.todo.app.domain.task.commands.UnstarTaskCommand;
import nl.avthart.todo.app.domain.task.events.TaskCompletedEvent;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TaskStarredEvent;
import nl.avthart.todo.app.domain.task.events.TaskTitleModifiedEvent;
import nl.avthart.todo.app.domain.task.events.TaskUnstarredEvent;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Task
 * @author albert
 */
@JsonAutoDetect(getterVisibility= Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, creatorVisibility = Visibility.NONE)
public class Task extends AbstractAnnotatedAggregateRoot<String> {

	/**
	 * The constant serialVersionUID 
	 */
	private static final long serialVersionUID = -5977984483620451665L;
	
	@AggregateIdentifier
	@JsonProperty 
	private String id;
	
	@NotNull
	@JsonProperty
	private boolean completed;
	
	/**
	 * Creates a new Task.
	 * 
	 * @param command create Task
	 */
	@CommandHandler
	public Task(CreateTaskCommand command) {
		apply(new TaskCreatedEvent(command.getId(), command.getUsername(), command.getTitle()));
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
		apply(new TaskCompletedEvent(command.getId()));
	}
	
	/**
	 * Stars a Task.
	 * 
	 * @param command star Task
	 */
	@CommandHandler
	void on(StarTaskCommand command) {
		apply(new TaskStarredEvent(command.getId()));
	}
	
	/**
	 * Unstars a Task.
	 * 
	 * @param command unstar Task
	 */
	@CommandHandler
	void on(UnstarTaskCommand command) {
		apply(new TaskUnstarredEvent(command.getId()));
	}
	
	/**
	 * Modifies a Task title.
	 * 
	 * @param command modify Task title
	 */
	@CommandHandler
	void on(ModifyTaskTitleCommand command) {
		assertNotCompleted();
		apply(new TaskTitleModifiedEvent(command.getId(), command.getTitle()));
	}
	
	@EventHandler
	void on(TaskCreatedEvent event) {
		this.id = event.getId();
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
