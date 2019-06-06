package com.ashkan.ie.repository;

import com.ashkan.ie.domain.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by K550 VX on 6/6/2019.
 */
@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {
}
