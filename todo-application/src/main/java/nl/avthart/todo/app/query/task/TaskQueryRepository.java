package nl.avthart.todo.app.query.task;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskQueryRepository extends PagingAndSortingRepository<TaskEntry, String> {
}
