package org.dgut.community.service.user;

import org.dgut.community.entity.User;
import org.dgut.community.entity.UserFollow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFollow {
    List<User> findByFansId(Long id, Pageable pageable);

    List<User> findByStarId(Long id, Pageable pageable);

    ResponseEntity<?> deleteById(Long id);

    UserFollow updateById(Long id);

    UserFollow save(UserFollow userFollow);
}
