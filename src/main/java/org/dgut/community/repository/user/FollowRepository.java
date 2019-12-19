package org.dgut.community.repository.user;

import org.dgut.community.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<UserFollow, Long> {
}
