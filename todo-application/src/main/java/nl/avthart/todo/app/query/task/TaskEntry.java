package nl.avthart.todo.app.query.task;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author albert
 */
@Entity
public class TaskEntry {

	@Id
	private String identifier;
	
	private String title;

	private boolean completed;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}	
	
	public boolean isCompleted() {
		return completed;
	}
}
