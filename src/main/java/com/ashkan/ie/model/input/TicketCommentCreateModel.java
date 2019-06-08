package com.ashkan.ie.model.input;

/**
 * Created by K550 VX on 6/7/2019.
 */
public class TicketCommentCreateModel {

    private Long ticketId;
    private String commentBody;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
}
