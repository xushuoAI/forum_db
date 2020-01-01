package org.dgut.community.service.user.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.User;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.user.IUser;
import org.dgut.community.util.ResultUtil;
import org.dgut.community.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements IUser {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).map(user -> {
            user.setUserPassword(null);
            return user;
        }).orElseThrow(()-> new NotFoundException(ResultEnum.USER_NOT_EXIST));
    }

    @Override
    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null){
            throw new NotFoundException(ResultEnum.USER_NOT_EXIST);
        }
        user.setUserPassword(null);
        return user;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return userRepository.findById(id).map(user -> {
            Util.deleteFile(user.getUserHeadImg());
            userRepository.delete(user);
            return ResponseEntity.ok(ResultUtil.success());
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public ResponseEntity<Result> updateById(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
//            user.setUserArticles(newUser.getUserArticles());
//            user.setUserFocus(newUser.getUserFocus());
//            user.setUserFollow(newUser.getUserFollow());
//            user.setUserHeadImg(newUser.getUserHeadImg());
//            if (newUser.getUserName() != ""){
//                if (userRepository.findByUserName(newUser.getUserName()) != null){
//                    throw new NotFoundException(ResultEnum.USER_IS_EXIST);
//                }
//                user.setUserName(newUser.getUserName());
//            }
            if (newUser.getUserHeadImg() != ""){
                Util.deleteFile(user.getUserHeadImg());
                user.setUserHeadImg(Util.uploadBase64Image(user.getUserName(), newUser.getUserHeadImg()));
            }
//            user.setUserName(newUser.getUserName());
            user = userRepository.save(user);
            user.setUserPassword(null);
            return ResponseEntity.ok(ResultUtil.success(user));
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public ResponseEntity<Result> updatePassword(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
            if (!user.getUserPassword().equals(DigestUtils.md5DigestAsHex(newUser.getUserPassword().getBytes()))){
                throw new NotFoundException(ResultEnum.PASSWORS_MISTAKE);
            }else {
                user.setUserPassword(DigestUtils.md5DigestAsHex(newUser.getNewPassword().getBytes()));
                user = userRepository.save(user);
                user.setUserPassword(null);
                return ResponseEntity.ok(ResultUtil.success(user));
            }
        }).orElseThrow(()-> new NotFoundException(ResultEnum.USER_NOT_EXIST));
    }

    @Override
    public ResponseEntity<Result> save(User user) {
        User user1 = userRepository.findByUserName(user.getUserName());
        if (user1 != null){
            throw new NotFoundException(ResultEnum.USER_IS_EXIST);
        }else if ("".equals(user.getUserPassword())){
//            responseHttpStatus.setMsg("密码不能为空");
            throw new NotFoundException(ResultEnum.PASSWORD_IS_NULL);
        }
        user.setUserHeadImg(Util.getUrl() + "abc.jpg");
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        user = userRepository.save(user);
        user.setUserPassword(null);
        return ResponseEntity.ok(ResultUtil.success(user));
    }

    @Override
    public ResponseEntity<Result> login(String name, String password, HttpSession session) {
        User user = userRepository.findByUserNameAndUserPassword(name, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user == null){
            throw new NotFoundException(ResultEnum.USER_PASSWORS_MISTAKE);
        }
        user.setUserPassword(null);
        session.setAttribute("user", user);
        return ResponseEntity.ok(ResultUtil.success(user));
    }
}
