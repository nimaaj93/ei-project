package com.ashkan.ie.repository;

import com.ashkan.ie.domain.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Repository
public interface TicketCommentRepository extends JpaRepository<TicketComment, Long> {
}
