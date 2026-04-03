package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import edu.helenacollege.hctickets.model.TicketComment;

public interface TicketCommentRepository extends JpaRepository<TicketComment, Integer> {
	List<TicketComment> findByTicketId(Integer ticketId);
}
