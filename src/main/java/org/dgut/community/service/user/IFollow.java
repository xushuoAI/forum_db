package org.dgut.community.service.user;

import org.dgut.community.entity.User;
import org.dgut.community.entity.UserFollow;
import org.dgut.community.resultenum.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFollow {
    List<User> findByFansId(Long id, Pageable pageable);

    List<User> findByStarId(Long id, Pageable pageable);

    ResponseEntity<?> deleteById(UserFollow follow);

    UserFollow updateById(Long id);

    ResponseEntity<Result> save(UserFollow userFollow);
}
