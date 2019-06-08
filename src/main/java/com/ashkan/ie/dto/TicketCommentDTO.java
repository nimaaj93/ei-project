package com.ashkan.ie.dto;

import com.ashkan.ie.enumeration.UserType;

/**
 * Created by K550 VX on 6/7/2019.
 */
public class TicketCommentDTO {

    private Long id;
    private String commentBody;
    private Long creatorUserId;
    private String creatorName;
    private UserType creatorUserType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
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
}
