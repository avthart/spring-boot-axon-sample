package nl.avthart.todo.app.query.task;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author albert
 */
@Entity
public class TaskEntry {

	@Id
	private String id;
	
	private String title;
	
	private boolean completed;
	
	private boolean starred;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public void setStarred(boolean starred) {
		this.starred = starred;
	}
	
	public boolean isStarred() {
		return starred;
	}	
}
