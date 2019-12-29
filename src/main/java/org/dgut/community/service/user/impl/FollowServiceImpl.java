package org.dgut.community.service.user.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.User;
import org.dgut.community.entity.UserFollow;
import org.dgut.community.repository.user.FollowRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.user.IFollow;
import org.dgut.community.util.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FollowServiceImpl implements IFollow {
    private FollowRepository followRepository;
    private UserRepository userRepository;

    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByFansId(Long id, Pageable pageable) {
        Page<UserFollow> userFollows = followRepository.findByFansId(id, pageable);
        List<Long> userId = new ArrayList<Long>();
        for (UserFollow follow : userFollows){
            userId.add(follow.getStarId());
        }
        Iterable<Long> longs = new Iterable<Long>() {
            @Override
            public Iterator<Long> iterator() {
                return userId.iterator();
            }
        };
        List<User> users = userRepository.findAllById(longs);
        for (User user : users){
            user.setUserPassword(null);
        }
        return users;
    }

    @Override
    public List<User> findByStarId(Long id, Pageable pageable) {
        Page<UserFollow> userFollows = followRepository.findByStarId(id, pageable);
        List<Long> userId = new ArrayList<Long>();
        for (UserFollow articleCollect : userFollows){
            userId.add(articleCollect.getFansId());
        }
        Iterable<Long> longs = new Iterable<Long>() {
            @Override
            public Iterator<Long> iterator() {
                return userId.iterator();
            }
        };
        List<User> users = userRepository.findAllById(longs);
        for (User user : users){
            user.setUserPassword(null);
        }
        return users;
    }

    @Override
    public ResponseEntity<?> deleteById(UserFollow follow) {
        UserFollow follow1 = followRepository.findByFansIdAndStarId(follow.getFansId(), follow.getStarId());
        return followRepository.findById(follow1.getFollowId()).map(userFollow -> {
            return userRepository.findById(userFollow.getStarId()).map(user -> {
                return userRepository.findById(userFollow.getFansId()).map(user1 -> {
                    user1.setUserFocus(user1.getUserFocus() - 1);
                    user.setUserFollow(user.getUserFollow() - 1);
                    userRepository.save(user1);
                    userRepository.save(user);
                    followRepository.delete(userFollow);
                    return ResponseEntity.ok(ResultUtil.success());
                }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
            }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public UserFollow updateById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Result> save(UserFollow userFollow) {
        UserFollow follow1 = followRepository.findByFansIdAndStarId(userFollow.getFansId(), userFollow.getStarId());
        if (follow1 != null){
            throw new NotFoundException(ResultEnum.NOT_REPEAT);
        }
        return userRepository.findById(userFollow.getStarId()).map(user -> {
            return userRepository.findById(userFollow.getFansId()).map(user1 -> {
                user1.setUserFocus(user1.getUserFocus() + 1);
                user.setUserFollow(user.getUserFollow() + 1);
                userRepository.save(user1);
                userRepository.save(user);
                UserFollow follow = followRepository.save(userFollow);
                return ResponseEntity.ok(ResultUtil.success(follow));
            }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }
}
