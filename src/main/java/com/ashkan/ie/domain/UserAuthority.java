package com.ashkan.ie.domain;

import com.ashkan.ie.enumeration.UserType;

import javax.persistence.*;

/**
 * Created by K550 VX on 6/6/2019.
 */
@Entity
@Table(name = "ie_user_authority")
public class UserAuthority extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private UserType authorityVal;
    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserType getAuthorityVal() {
        return authorityVal;
    }

    public void setAuthorityVal(UserType authorityVal) {
        this.authorityVal = authorityVal;
    }
}
