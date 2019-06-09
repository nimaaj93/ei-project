package com.ashkan.ie.mapper;

import com.ashkan.ie.domain.Ticket;
import com.ashkan.ie.dto.TicketCommentDTO;
import com.ashkan.ie.dto.TicketDTO;
import com.ashkan.ie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Component
public class TicketMapper implements EntityMapper<TicketDTO, Ticket> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public TicketDTO toDto(Ticket entity) {

        TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setId(entity.getId());
        ticketDTO.setTicketTitle(entity.getTicketTitle());
        ticketDTO.setTicketBody(entity.getTicketBody());
        ticketDTO.setTicketStatus(entity.getTicketStatus());
        ticketDTO.setAssigneeUserId(entity.getAssigneeUserId());
        ticketDTO.setCreatorUserId(entity.getCreatorUserId());

        userRepository.findById(entity.getAssigneeUserId())
                .ifPresent(user -> {
                    ticketDTO.setAssigneeName(user.getFullname());
                    ticketDTO.setAssigneeUserType(user.getUserAuthority().getAuthorityVal());
                });

        userRepository.findById(entity.getCreatorUserId())
                .ifPresent(user -> {
                    ticketDTO.setCreatorName(user.getFullname());
                    ticketDTO.setCreatorUserType(user.getUserAuthority().getAuthorityVal());
                });

        if (entity.getComments() != null) {
            List<TicketCommentDTO> comments = entity.getComments().stream()
                    .map(ticketComment -> {
                        TicketCommentDTO commentDTO = new TicketCommentDTO();
                        commentDTO.setId(ticketComment.getId());
                        commentDTO.setCommentBody(ticketComment.getCommentBody());

                        userRepository.findById(ticketComment.getCreatorUserId())
                                .ifPresent(user -> {
                                    commentDTO.setCreatorName(user.getFullname());
                                    commentDTO.setCreatorUserType(user.getUserAuthority().getAuthorityVal());
                                });
                        return commentDTO;
                    }).collect(Collectors.toList());
            ticketDTO.setComments(comments);
        }

        return ticketDTO;
    }

    @Override
    public Ticket toEntity(TicketDTO dto) {
        return null;
    }
}
