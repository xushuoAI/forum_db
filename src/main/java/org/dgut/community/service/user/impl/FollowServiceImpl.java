package org.dgut.community.service.user.impl;

import org.dgut.community.entity.UserFollow;
import org.dgut.community.repository.user.FollowRepository;
import org.dgut.community.service.user.IFollow;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements IFollow {
    private FollowRepository followRepository;

    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public UserFollow findById(Long id) {
        return null;
    }

    @Override
    public UserFollow deleteById(Long id) {
        return null;
    }

    @Override
    public UserFollow updateById(Long id) {
        return null;
    }

    @Override
    public UserFollow save() {
        return null;
    }
}
