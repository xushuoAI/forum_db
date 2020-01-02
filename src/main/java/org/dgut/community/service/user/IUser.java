package org.dgut.community.service.user;

import org.dgut.community.entity.User;
import org.dgut.community.resultenum.Result;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUser {
    List<User> findByUserNameLike(String userName, Long myId);

    User findById(Long userId, Long myId);

    User findByUserName(String userName, Long myId);

    ResponseEntity<?> deleteById(Long id);

    ResponseEntity<Result> updateById(Long id, User newUser);

    ResponseEntity<Result> updatePassword(Long id, User newUser);

    ResponseEntity<Result> save(User user);

    ResponseEntity<Result> login(String name, String password, HttpSession session);
}
