package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import edu.helenacollege.hctickets.model.Ticket;
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	List<Ticket> findByApplicationIdAndStatus(Integer applicationId, String status);
}


