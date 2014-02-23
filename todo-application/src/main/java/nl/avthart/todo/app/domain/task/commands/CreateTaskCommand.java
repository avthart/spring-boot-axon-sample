package nl.avthart.todo.app.domain.task.commands;

import nl.avthart.todo.app.domain.support.AbstractIdentifierCommand;

/**
 * @author albert
 */
public class CreateTaskCommand extends AbstractIdentifierCommand {

	private final String title;

	public CreateTaskCommand(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}	
}
