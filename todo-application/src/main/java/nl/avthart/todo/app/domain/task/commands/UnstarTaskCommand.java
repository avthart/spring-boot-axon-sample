package nl.avthart.todo.app.domain.task.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author albert
 */
public class UnstarTaskCommand {

	@TargetAggregateIdentifier
	private final String identifier;
	
	public UnstarTaskCommand(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
