package nl.avthart.todo.app.domain.task.commands;

import javax.validation.constraints.NotNull;

import lombok.Value;

/**
 * @author albert
 */
@Value
public class CreateTaskCommand {

	@NotNull
	private final String id;
	
	@NotNull
	private final String username;
	
	@NotNull
	private final String title;
}
