package nl.avthart.todo.app.query.task;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author albert
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class TaskEntry {

	private String id;
	
	private String username;
	
	@Setter
	private String title;
	
	@Setter
	private boolean completed;
	
	@Setter
	private boolean starred;
}