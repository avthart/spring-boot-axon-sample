package nl.avthart.todo.app.domain.task.commands;

import nl.avthart.todo.app.domain.support.AbstractIdentifierCommand;

/**
 * @author albert
 */
public class CreateTaskCommand extends AbstractIdentifierCommand {

	private final String username;
	
	private final String title;

	public CreateTaskCommand(String username, String title) {
		this.username = username;
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getUsername() {
		return username;
	}
}
