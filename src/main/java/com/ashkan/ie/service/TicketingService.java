package com.ashkan.ie.service;

import com.ashkan.ie.dto.TicketDTO;
import com.ashkan.ie.model.input.TicketCommentCreateModel;
import com.ashkan.ie.model.input.TicketCreateModel;
import com.ashkan.ie.model.input.TicketStatusUpdateModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by K550 VX on 6/7/2019.
 */
public interface TicketingService {

    Page<TicketDTO> getAll(Pageable pageable);

    TicketDTO createTicket(TicketCreateModel model);

    List<TicketDTO> getUserAssignedTickets();

    List<TicketDTO> getUserCreatedTickets();

    TicketDTO addComment(TicketCommentCreateModel model);

    TicketDTO updateStatus(TicketStatusUpdateModel model);

    TicketDTO getTicket(Long id);

}