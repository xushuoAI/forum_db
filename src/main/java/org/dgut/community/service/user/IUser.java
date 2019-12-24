package org.dgut.community.service.user;

import org.dgut.community.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface IUser {

    String deleteById(Long id);

    User updateById(Long id, User newUser);

    User save(User user, MultipartFile file);

    User login(String name, String password);
}
