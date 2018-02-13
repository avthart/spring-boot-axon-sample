package nl.avthart.todo.app.domain.task.commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author albert
 */
@Value
public class StarTaskCommand {

	@TargetAggregateIdentifier
	private final String id;
}
