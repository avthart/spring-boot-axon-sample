package nl.avthart.todo.app.query.task;

import io.orchestrate.client.Client;
import io.orchestrate.client.Result;
import io.orchestrate.client.SearchResults;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * @author albert
 */
@Component
public class TaskEntryRepository {
	
	private final static String COLLECTION = "task-entries";
	
	private Client client;
	
	@Autowired
	public TaskEntryRepository(Client client) {
		this.client = client;
	}
	
	public TaskEntry findOne(String id) {
		return client.kv(COLLECTION, id).get(TaskEntry.class).get().getValue();
	}
	
	public void save(TaskEntry taskEntry) {
		client.kv(COLLECTION, taskEntry.getId()).put(taskEntry).get();
	}

	public Page<TaskEntry> findByUsernameAndCompleted(String username, boolean completed, Pageable pageable) {
		SearchResults<TaskEntry> searchResults = client.searchCollection(COLLECTION)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.get(TaskEntry.class, "username:" + username + " AND completed:" + completed)
		.get();
		
		List<TaskEntry> entries = new ArrayList<TaskEntry>();
		Iterable<Result<TaskEntry>> results = searchResults.getResults();
		for (Result<TaskEntry> result : results) {
			entries.add(result.getKvObject().getValue());
		}			
		
		return new PageImpl<TaskEntry>(entries, pageable, searchResults.getCount());
	}
}
