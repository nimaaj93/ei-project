package com.ashkan.ie.domain;

import com.ashkan.ie.enumeration.UserStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by K550 VX on 6/6/2019.
 */
@Entity
@Table(name = "ie_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullname;
    private String email;

    @OneToOne(mappedBy = "user")
    private UserAuthentication userAuthentication;

    @OneToOne(mappedBy = "user")
    private UserAuthority userAuthority;
    private boolean activated;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
