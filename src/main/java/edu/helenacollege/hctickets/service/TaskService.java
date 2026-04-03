package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.dto.TaskCreateDto;
import edu.helenacollege.hctickets.dto.TaskResponseDto;
import edu.helenacollege.hctickets.dto.TaskUpdateDto;

public interface TaskService {

    TaskResponseDto create(TaskCreateDto dto);

    TaskResponseDto update(Integer id, TaskUpdateDto dto);

    TaskResponseDto findById(Integer id);

    List<TaskResponseDto> findAll();
    
    List<TaskResponseDto> findByTicketId(Integer ticketId);

    void delete(Integer id);
}