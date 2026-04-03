package edu.helenacollege.hctickets.controller;

import edu.helenacollege.hctickets.dto.TicketCreateDto;
import edu.helenacollege.hctickets.dto.TicketResponseDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleResponseDto;
import edu.helenacollege.hctickets.service.TaskService;
import edu.helenacollege.hctickets.service.TicketCommentService;
import edu.helenacollege.hctickets.service.TicketService;
import edu.helenacollege.hctickets.service.UserApplicationRoleService;
import edu.helenacollege.hctickets.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	private final TicketService ticketService;
	private final UserApplicationRoleService userApplicationRoleService;
	private final TicketCommentService ticketCommentService;
	private final TaskService taskService;
	
	public TicketController(
	        TicketService ticketService,
	        UserApplicationRoleService userApplicationRoleService,
	        TicketCommentService ticketCommentService,
	        TaskService taskService) {

	    this.ticketService = ticketService;
		this.userApplicationRoleService = userApplicationRoleService;
	    this.ticketCommentService = ticketCommentService;
	    this.taskService = taskService;
	}
	
	@GetMapping("/new")
	public String newTicketForm(Model model) {
		Integer userId = 1;
		
		
		List<UserApplicationRoleResponseDto> applications = 
				userApplicationRoleService.getByUserId(userId);
		
		model.addAttribute("applications", applications);
		model.addAttribute("ticket", new TicketCreateDto(null, null, null, null, null, null, null));
		
		return "ticket/form";
	}
	
	@GetMapping("/app/{applicationId}")
	public String getTicketsByApplication(
	        @PathVariable Integer applicationId,
	        Model model) {
		
	    if (applicationId == null || applicationId <= 0) {
	        model.addAttribute("error", "Invalid application ID");
	        return "error";
	    }

	    List<TicketResponseDto> tickets =
	            ticketService.getOpenTicketsByApplicationId(applicationId);
	    
	    if (tickets.isEmpty()) {
	        model.addAttribute("message", "No open tickets for this application");
	    }

	    model.addAttribute("tickets", tickets);
	    model.addAttribute("applicationId", applicationId);

	    return "ticket/list";
	}
	
	@PostMapping
	public String createTicket(@ModelAttribute TicketCreateDto ticketCreateDto) {
		
		ticketService.create(ticketCreateDto);
		
		return "redirect:/tickets";
		
	}
	
	@GetMapping("/{ticketId}")
	public String viewTicket(
	        @PathVariable Integer ticketId,
	        Model model) {

	    if (ticketId == null || ticketId <= 0) {
	        model.addAttribute("error", "Invalid ticket ID");
	        return "error";
	    }

	    TicketResponseDto ticket = ticketService.findById(ticketId);

	    if (ticket == null) {
	        model.addAttribute("error", "Ticket not found");
	        return "error";
	    }

	    Integer userId = 1;

	    var userApps = userApplicationRoleService.getByUserId(userId);

	    boolean hasAccess = userApps.stream()
	            .anyMatch(app -> app.appId().equals(ticket.applicationId()));

	    if (!hasAccess) {
	        model.addAttribute("error", "You do not have access to this ticket");
	        return "error";
	    }

	    var comments = ticketCommentService.findByTicketId(ticketId);
	    var tasks = taskService.findByTicketId(ticketId);

	    model.addAttribute("ticket", ticket);
	    model.addAttribute("comments", comments);
	    model.addAttribute("tasks", tasks);

	    return "ticket/view";
	}
}
