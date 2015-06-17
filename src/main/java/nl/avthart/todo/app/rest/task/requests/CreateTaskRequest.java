package nl.avthart.todo.app.rest.task.requests;

import javax.validation.constraints.NotNull;

/**
 * @author albert
 */
public class CreateTaskRequest {
	
	@NotNull
	private String title;
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
