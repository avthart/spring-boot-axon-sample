package nl.avthart.todo.app.domain.task;

/**
 * @author albert
 */
public class TaskAlreadyCompletedException extends RuntimeException {

	private static final long serialVersionUID = 1518440584190922771L;

	public TaskAlreadyCompletedException(String message) {
		super(message);
	}
}
