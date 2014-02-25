package nl.avthart.todo.app.domain.task.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author albert
 */
public class StarTaskCommand {

	@TargetAggregateIdentifier
	private final String identifier;
	
	public StarTaskCommand(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
