package nl.avthart.todo.app.query.task;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author albert
 */
public interface TaskQueryRepository extends PagingAndSortingRepository<TaskEntry, String> {
}
