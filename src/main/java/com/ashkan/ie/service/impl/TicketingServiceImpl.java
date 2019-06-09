package com.ashkan.ie.service.impl;

import com.ashkan.ie.domain.Ticket;
import com.ashkan.ie.domain.TicketComment;
import com.ashkan.ie.domain.User;
import com.ashkan.ie.dto.TicketDTO;
import com.ashkan.ie.enumeration.TicketStatus;
import com.ashkan.ie.enumeration.UserType;
import com.ashkan.ie.exception.InvalidInputDataException;
import com.ashkan.ie.exception.TicketNotFoundException;
import com.ashkan.ie.mapper.TicketMapper;
import com.ashkan.ie.model.input.TicketCommentCreateModel;
import com.ashkan.ie.model.input.TicketCreateModel;
import com.ashkan.ie.model.input.TicketStatusUpdateModel;
import com.ashkan.ie.repository.TicketCommentRepository;
import com.ashkan.ie.repository.TicketRepository;
import com.ashkan.ie.repository.UserRepository;
import com.ashkan.ie.service.TicketingService;
import com.ashkan.ie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Service
public class TicketingServiceImpl implements TicketingService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketCommentRepository ticketCommentRepository;

    @Override
    public Page<TicketDTO> getAll(Pageable pageable) {
        return ticketRepository.findAll(pageable).map(ticketMapper::toDto);
    }

    @Override
    public TicketDTO createTicket(TicketCreateModel model) {

        User user = userRepository.findById(model.getAssigneeUserId())
                .orElseThrow(InvalidInputDataException::new);

        if (user.getUserAuthority().getAuthorityVal() == UserType.STUDENT) {
            throw new InvalidInputDataException();
        }

        Ticket ticket = mapToEntity(model);
        return ticketMapper.toDto(ticketRepository.save(ticket));
    }

    private Ticket mapToEntity(TicketCreateModel model) {
        Ticket ticket = new Ticket();

        ticket.setTicketTitle(model.getTicketTitle());
        ticket.setTicketBody(model.getTicketBody());
        ticket.setAssigneeUserId(model.getAssigneeUserId());
        ticket.setTicketStatus(TicketStatus.NEW);
        ticket.setCreatorUserId(userService.getProfile().getId());

        return ticket;
    }

    @Override
    public List<TicketDTO> getUserAssignedTickets() {
        Long userId = userService.getProfile().getId();
        return ticketMapper.toDto(ticketRepository.findAllByAssigneeUserId(userId));
    }

    @Override
    public List<TicketDTO> getUserCreatedTickets() {
        Long userId = userService.getProfile().getId();
        return ticketMapper.toDto(ticketRepository.findAllByCreatorUserId(userId));
    }

    @Override
    public TicketDTO addComment(TicketCommentCreateModel model) {

        Ticket ticket = ticketRepository.findById(model.getTicketId())
                .orElseThrow(TicketNotFoundException::new);

        TicketComment comment = new TicketComment();
        comment.setCreatorUserId(userService.getProfile().getId());
        comment.setCommentBody(model.getCommentBody());
        comment.setTicket(ticket);

        ticketCommentRepository.save(comment);

        List<TicketComment> ticketComments = ticket.getComments();

        if (ticketComments == null) {
            ticketComments = new ArrayList<>();
        }
        ticketComments.add(comment);

        return ticketMapper.toDto(ticket);
    }

    @Override
    public TicketDTO updateStatus(TicketStatusUpdateModel model) {

        Ticket ticket = ticketRepository.findById(model.getId())
                .orElseThrow(TicketNotFoundException::new);

        ticket.setTicketStatus(model.getStatus());
        Ticket persistedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(persistedTicket);
    }

    @Override
    public TicketDTO getTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);

        return ticketMapper.toDto(ticket);
    }
}
