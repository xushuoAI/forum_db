package org.dgut.community.service.user;

import org.dgut.community.entity.User;
import org.springframework.http.ResponseEntity;

public interface IUser {

    User findByUserName(String userName);

    ResponseEntity<?> deleteById(Long id);

    User updateById(Long id, User newUser);

    ResponseEntity<User> updatePassword(Long id, User newUser);

    ResponseEntity<User> save(User user);

    User login(String name, String password);
}
