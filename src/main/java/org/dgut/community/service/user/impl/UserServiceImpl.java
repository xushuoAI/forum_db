package org.dgut.community.service.user.impl;

import org.dgut.community.entity.User;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.service.user.IUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUser {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User deleteById(Long id) {
        return null;
    }

    @Override
    public User updateById(Long id) {
        return null;
    }

    @Override
    public User save() {
        return null;
    }
}
