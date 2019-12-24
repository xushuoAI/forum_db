package org.dgut.community.repository.user;

import org.dgut.community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameAndUserPassword(String name, String password);
}
