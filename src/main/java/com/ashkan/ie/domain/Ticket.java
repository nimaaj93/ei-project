package com.ashkan.ie.domain;

import com.ashkan.ie.enumeration.TicketStatus;

import javax.persistence.*;
import java.util.List;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Entity
@Table(name = "ie_ticket")
public class Ticket extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ticketTitle;
    private String ticketBody;
    private Long assigneeUserId;
    private Long creatorUserId;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    private List<TicketComment> comments;

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

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public List<TicketComment> getComments() {
        return comments;
    }

    public void setComments(List<TicketComment> comments) {
        this.comments = comments;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}