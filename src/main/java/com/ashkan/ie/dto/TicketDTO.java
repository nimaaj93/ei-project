package com.ashkan.ie.dto;

import com.ashkan.ie.enumeration.TicketStatus;
import com.ashkan.ie.enumeration.UserType;

import java.util.List;

/**
 * Created by K550 VX on 6/7/2019.
 */
public class TicketDTO {

    private Long id;
    private String ticketTitle;
    private String ticketBody;
    private Long assigneeUserId;
    private String assigneeName;
    private Long creatorUserId;
    private String creatorName;
    private UserType creatorUserType;
    private UserType assigneeUserType;
    private TicketStatus ticketStatus;

    private List<TicketCommentDTO> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public UserType getCreatorUserType() {
        return creatorUserType;
    }

    public void setCreatorUserType(UserType creatorUserType) {
        this.creatorUserType = creatorUserType;
    }

    public UserType getAssigneeUserType() {
        return assigneeUserType;
    }

    public void setAssigneeUserType(UserType assigneeUserType) {
        this.assigneeUserType = assigneeUserType;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public List<TicketCommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<TicketCommentDTO> comments) {
        this.comments = comments;
    }
}
