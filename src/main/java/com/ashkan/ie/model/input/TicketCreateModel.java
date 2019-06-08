package com.ashkan.ie.model.input;

/**
 * Created by K550 VX on 6/7/2019.
 */
public class TicketCreateModel {

    private String ticketTitle;
    private String ticketBody;
    private Long assigneeUserId;

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public String getTicketBody() {
        return ticketBody;
    }

    public void setTicketBody(String ticketBody) {
        this.ticketBody = ticketBody;
    }

    public Long getAssigneeUserId() {
        return assigneeUserId;
    }

    public void setAssigneeUserId(Long assigneeUserId) {
        this.assigneeUserId = assigneeUserId;
    }
}
