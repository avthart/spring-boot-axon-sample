package nl.avthart.todo.app.query.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author albert
 */
public interface TaskQueryRepository extends PagingAndSortingRepository<TaskEntry, String> {
	Page<TaskEntry> findByCompleted(boolean completed, Pageable pageable);
}
