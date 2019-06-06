package com.ashkan.ie.repository;

import com.ashkan.ie.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by K550 VX on 6/6/2019.
 */
@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}
