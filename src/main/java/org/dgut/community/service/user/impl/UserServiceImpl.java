package org.dgut.community.service.user.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.service.user.IUser;
import org.dgut.community.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import java.util.Set;

@Service
public class UserServiceImpl implements IUser {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        user.setUserPassword(null);
        return user;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new NotFoundException("没有该id"));
    }

    @Override
    public User updateById(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
//            user.setUserArticles(newUser.getUserArticles());
//            user.setUserFocus(newUser.getUserFocus());
//            user.setUserFollow(newUser.getUserFollow());
//            user.setUserHeadImg(newUser.getUserHeadImg());
            if (newUser.getUserHeadImg() != null){
                user.setUserHeadImg(Util.uploadBase64Image(user.getUserName(), newUser.getUserHeadImg()));
            }
//            user.setUserName(newUser.getUserName());
            user = userRepository.save(user);
            user.setUserPassword(null);
            return user;
        }).orElseThrow(()-> new NotFoundException("没有该id"));
    }

    @Override
    public ResponseEntity<User> updatePassword(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }).orElseThrow(()-> new NotFoundException("没有该用户"));
    }

    @Override
    public User save(User user) {
//        System.out.println(file.getOriginalFilename());
        User user1 = userRepository.findByUserName(user.getUserName());
        if (user1 != null){
            throw new NotFoundException("该用户已存在");
        }
        if (user.getUserHeadImg() == null){
            user.setUserHeadImg(Util.getUrl() + "abc.jpg");
        }else {
            user.setUserHeadImg(Util.uploadBase64Image(user.getUserName(), user.getUserHeadImg()));
        }
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        user = userRepository.save(user);
        user.setUserPassword(null);
        return user;
    }

    @Override
    public User login(String name, String password) {
        User user = userRepository.findByUserNameAndUserPassword(name, DigestUtils.md5DigestAsHex(password.getBytes()));
//        if (user.getUserHeadImg() != null){
//            user.setUserHeadImg(Util.getUrl() + user.getUserName() + "/" + user.getUserHeadImg());
//        }else {
//            user.setUserHeadImg(Util.getUrl() + "abc.jpg");
//        }
        if (user == null){
            throw new NotFoundException("用户名或密码错误");
        }
        user.setUserPassword(null);
        return user;
    }
}
