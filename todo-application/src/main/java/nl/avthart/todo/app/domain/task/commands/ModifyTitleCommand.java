package nl.avthart.todo.app.domain.task.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author albert
 */
public class ModifyTitleCommand {

	@TargetAggregateIdentifier
	private final String identifier;
	
	private final String title;

	public ModifyTitleCommand(String identifier, String title) {
		this.identifier = identifier;
		this.title = title;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getTitle() {
		return title;
	}	
}
