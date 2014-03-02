package nl.avthart.todo.app.query.task;

public class TaskQueryEvent {
	
	public enum Type { ADDED, COMPLETED, MODIFIED }
	
	private Type type;
	
	private TaskEntry data;

	public TaskQueryEvent(Type type, TaskEntry data) {
		this.type = type;
		this.data = data;		
	}	
	
	public TaskEntry getData() {
		return data;
	}
	
	public Type getType() {
		return type;
	}
}
