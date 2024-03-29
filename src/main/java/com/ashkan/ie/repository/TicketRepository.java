package com.ashkan.ie.repository;

import com.ashkan.ie.domain.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Page<Ticket> findAllByAssigneeUserId(Pageable pageable, Long assigneeUserId);

    Page<Ticket> findAllByCreatorUserId(Pageable pageable, Long creatorUserId);

}