package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import edu.helenacollege.hctickets.dto.TicketCommentResponseDto;
import edu.helenacollege.hctickets.dto.TicketCommentUpdateDto;
import edu.helenacollege.hctickets.model.TicketComment;
import edu.helenacollege.hctickets.dto.TicketCommentCreateDto;

@Mapper(componentModel = "spring")
public interface TicketCommentMapper {

    TicketCommentResponseDto toResponseDto(TicketComment entity);

    void updateEntity(TicketCommentUpdateDto dto, @MappingTarget TicketComment entity);
    
    TicketComment toEntity(TicketCommentCreateDto dto);
}