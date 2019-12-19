package org.dgut.community.service.user;

import org.dgut.community.entity.UserFollow;

public interface IFollow {
    UserFollow findById(Long id);

    UserFollow deleteById(Long id);

    UserFollow updateById(Long id);

    UserFollow save();
}
