package org.dgut.community.repository.user;

import org.dgut.community.entity.UserFollow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<UserFollow, Long> {
    Page<UserFollow> findByFansId(Long id, Pageable pageable);
    Page<UserFollow> findByStarId(Long id, Pageable pageable);
}
