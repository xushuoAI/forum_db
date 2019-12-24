package org.dgut.community.service.user.impl;

import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.service.user.IUser;
import org.dgut.community.util.Util;
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
    public String deleteById(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return "删除成功";
        }).orElseThrow(()-> new RuntimeException("没有该id"));
    }

    @Override
    public User updateById(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setUserArticles(newUser.getUserArticles());
            user.setUserFocus(newUser.getUserFocus());
            user.setUserFollow(newUser.getUserFollow());
            user.setUserHeadImg(newUser.getUserHeadImg());
//            user.setUserName(newUser.getUserName());
            user = userRepository.save(user);
//            user.setUserPassword(null);
            return user;
        }).orElseThrow(()-> new RuntimeException("没有该id"));
    }

    @Override
    public User save(User user, MultipartFile file) {
//        System.out.println(file.getOriginalFilename());
        user.setUserHeadImg(user.getUserName() + file.getOriginalFilename());
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        user = userRepository.save(user);
        Util.upload(file, user.getUserName(), user.getUserName());
//        user.setUserPassword(null);
        return user;
    }

    @Override
    public User login(String name, String password) {
        User user = userRepository.findByUserNameAndUserPassword(name, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user.getUserHeadImg() != null){
            user.setUserHeadImg(Util.getUrl() + user.getUserName() + "/" + user.getUserHeadImg());
        }else {
            user.setUserHeadImg(Util.getUrl() + "abc.jpg");
        }
        return user;
    }
}
