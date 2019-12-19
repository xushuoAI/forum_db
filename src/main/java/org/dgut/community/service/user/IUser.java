package org.dgut.community.service.user;

import org.dgut.community.entity.User;

public interface IUser {
    User findById(Long id);

    User deleteById(Long id);

    User updateById(Long id);

    User save();
}
