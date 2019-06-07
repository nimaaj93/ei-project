package com.ashkan.ie.repository;

import com.ashkan.ie.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by K550 VX on 6/6/2019.
 */
@Repository
public interface UserRepository extends
        JpaRepository<User,Long>,
        PagingAndSortingRepository<User,Long> {

    Optional<User> findOneWithAuthoritiesByEmail(String email);

    Optional<User> findOneByEmail(String email);

    Page<User> findAllByActivatedFalse(Pageable pageable);

}