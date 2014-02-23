package nl.avthart.todo.app.domain.task;

public class TaskCompletedException extends RuntimeException {

	private static final long serialVersionUID = 1518440584190922771L;

	public TaskCompletedException(String message) {
		super(message);
	}
}
