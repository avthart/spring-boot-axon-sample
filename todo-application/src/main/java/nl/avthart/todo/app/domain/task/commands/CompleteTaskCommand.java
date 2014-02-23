package nl.avthart.todo.app.domain.task.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CompleteTaskCommand {

	@TargetAggregateIdentifier
	private final String identifier;
	
	public CompleteTaskCommand(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
