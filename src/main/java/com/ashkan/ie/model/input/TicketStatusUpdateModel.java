package com.ashkan.ie.model.input;

import com.ashkan.ie.enumeration.TicketStatus;

/**
 * Created by K550 VX on 6/8/2019.
 */
public class TicketStatusUpdateModel {

    private Long id;
    private TicketStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
