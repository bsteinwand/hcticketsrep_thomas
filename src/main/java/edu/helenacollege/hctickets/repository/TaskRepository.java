package edu.helenacollege.hctickets.repository;

import java.util.*;
import javax.xml.stream.events.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.helenacollege.hctickets.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	List<Comment> findByTicketId(Integer ticketId);
}
