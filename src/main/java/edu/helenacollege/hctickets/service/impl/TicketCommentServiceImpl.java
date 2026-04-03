package edu.helenacollege.hctickets.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.dto.TicketCommentCreateDto;
import edu.helenacollege.hctickets.dto.TicketCommentResponseDto;
import edu.helenacollege.hctickets.dto.TicketCommentUpdateDto;
import edu.helenacollege.hctickets.model.TicketComment;
import edu.helenacollege.hctickets.repository.TicketCommentRepository;
import edu.helenacollege.hctickets.mapper.TicketCommentMapper;
import edu.helenacollege.hctickets.service.TicketCommentService;

@Service
@Transactional
public class TicketCommentServiceImpl implements TicketCommentService {

    private final TicketCommentRepository repository;
    private final TicketCommentMapper mapper;

    public TicketCommentServiceImpl(
            TicketCommentRepository repository,
            TicketCommentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TicketCommentResponseDto create(TicketCommentCreateDto dto) {
        TicketComment entity = mapper.toEntity(dto);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public TicketCommentResponseDto update(Integer id, TicketCommentUpdateDto dto) {

        TicketComment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        entity.setCommentText(dto.commentText());

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public TicketCommentResponseDto findById(Integer id) {

        TicketComment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return mapper.toResponseDto(entity);
    }

    @Override
    public List<TicketCommentResponseDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public List<TicketCommentResponseDto> findByTicketId(Integer ticketId) {

        List<TicketComment> comments =
                repository.findByTicketId(ticketId);

        return comments.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}