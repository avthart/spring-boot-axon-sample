package nl.avthart.todo.app.rest.task;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import nl.avthart.todo.app.domain.task.commands.CreateTaskCommand;
import nl.avthart.todo.app.query.task.TaskEntry;
import nl.avthart.todo.app.query.task.TaskQueryRepository;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	@Autowired
	private TaskQueryRepository taskQueryRepository;
	
	@Autowired
	private CommandGateway commandGateway;
	
	@RequestMapping(value = "/api/tasks", method = RequestMethod.GET)
	public @ResponseBody Page<TaskEntry> tasks(Pageable pageRequest) {
		return taskQueryRepository.findAll(pageRequest);	
	}
	
	@RequestMapping(value = "/api/tasks", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createTask(@RequestBody @Valid CreateTaskRequest request) {
		commandGateway.sendAndWait(new CreateTaskCommand(request.getTitle()));
	}
	
	public static class CreateTaskRequest {
		@NotNull
		private String title;
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return title;
		}
	}
}
