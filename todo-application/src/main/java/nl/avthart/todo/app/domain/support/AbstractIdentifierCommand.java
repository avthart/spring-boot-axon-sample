package nl.avthart.todo.app.domain.support;

import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * Abstract Command class which generates an UUID identifier on creation 
 * @author albert
 */
public abstract class AbstractIdentifierCommand {

	@NotNull
	private String identifier;
	
	public AbstractIdentifierCommand() {
		identifier = UUID.randomUUID().toString();
	}
	
	/**
	 * Get the identifier
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
}
