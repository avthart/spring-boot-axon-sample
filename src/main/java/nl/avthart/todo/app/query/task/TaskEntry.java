package nl.avthart.todo.app.query.task;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author albert
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = { "id" })
public class TaskEntry {

	@Id
	private String id;
	
	private String username;
	
	@Setter
	private String title;
	
	@Setter
	private boolean completed;
	
	@Setter
	private boolean starred;
}