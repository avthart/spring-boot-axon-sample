package nl.avthart.todo.app.notify.task;

import lombok.Value;
import nl.avthart.todo.app.domain.task.events.TaskEvent;

@Value
public class TaskEventNotification {
	
	private String type;
	
	private TaskEvent data;
}
