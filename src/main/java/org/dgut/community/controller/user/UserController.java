package org.dgut.community.controller.user;

import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.service.user.impl.UserServiceImpl;
import org.dgut.community.util.Util;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<String, Object> map = new HashMap<String, Object>();
    private UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Map login(String name, String password, HttpSession session){
        User user = service.login(name, password);
        if (user != null){
            map.clear();
            user.setUserPassword(null);
            map.put("message", "登录成功");
            map.put("user", user);
            session.setAttribute("user", user);
        }else {
            map.put("message", "用户名或密码有误");
        }
        return map;
    }

    @GetMapping("/logout")
    public Map logout(HttpSession session) {
        session.removeAttribute("user");
        map.clear();
        map.put("message", "注销成功");
        return map;
    }

    @PostMapping("/save")
    public User save(User entity,  MultipartFile file){
        return service.save(entity, file);
    }

    @PutMapping("/updateById/{id}")
    public User updateById(@PathVariable Long id, @RequestBody User newUser){
        return service.updateById(id, newUser);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public Map<String, Object> deleteById(@PathVariable Long id){
        map.clear();
        map.put("message", service.deleteById(id));
        return map;
    }
}
