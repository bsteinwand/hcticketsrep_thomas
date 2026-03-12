package edu.helenacollege.hctickets.controller;

import edu.helenacollege.hctickets.dto.TicketCreateDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleResponseDto;
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
	private final UserService userService;
	private final UserApplicationRoleService userApplicationRoleService;
	
	public TicketController(
			TicketService ticketService,
			UserService userService,
			UserApplicationRoleService userApplicationRoleService) {
		this.ticketService = ticketService;
		this.userService = userService;
		this.userApplicationRoleService = userApplicationRoleService;
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
	
	@PostMapping
	public String createTicket(@ModelAttribute TicketCreateDto ticketCreateDto) {
		
		ticketService.create(ticketCreateDto);
		
		return "redirect:/tickets";
		
	}
}
