package org.dgut.community.service.user.impl;

import org.dgut.community.entity.UserFollow;
import org.dgut.community.repository.user.FollowRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.service.user.IFollow;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements IFollow {
    private FollowRepository followRepository;
    private UserRepository userRepository;

    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public UserFollow findById(Long id) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return followRepository.findById(id).map(userFollow -> {
            return userRepository.findById(userFollow.getStarId()).map(user -> {
                return userRepository.findById(userFollow.getFansId()).map(user1 -> {
                    user1.setUserFocus(user1.getUserFocus() - 1);
                    user.setUserFollow(user.getUserFollow() - 1);
                    userRepository.save(user1);
                    userRepository.save(user);
                    followRepository.delete(userFollow);
                    return "删除成功";
                }).orElseThrow(()-> new RuntimeException("没有该用户"));
            }).orElseThrow(()-> new RuntimeException("没有该用户"));
        }).orElseThrow(()-> new RuntimeException("没有关注该用户"));
    }

    @Override
    public UserFollow updateById(Long id) {
        return null;
    }

    @Override
    public UserFollow save(UserFollow userFollow) {
        return userRepository.findById(userFollow.getStarId()).map(user -> {
            return userRepository.findById(userFollow.getFansId()).map(user1 -> {
                user1.setUserFocus(user1.getUserFocus() + 1);
                user.setUserFollow(user.getUserFollow() + 1);
                userRepository.save(user1);
                userRepository.save(user);
                return followRepository.save(userFollow);
            }).orElseThrow(()-> new RuntimeException("没有该用户"));
        }).orElseThrow(()-> new RuntimeException("没有该用户"));
    }
}
