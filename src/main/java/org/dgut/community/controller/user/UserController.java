package org.dgut.community.controller.user;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.User;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.user.impl.UserServiceImpl;
import org.dgut.community.session.MySessionContext;
import org.dgut.community.util.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<String, Object> map = new HashMap<String, Object>();
    private UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody User user, HttpSession session){
        return service.login(user.getUserName(), user.getUserPassword(), session);
//        if (user != null){
//            map.clear();
//            user.setUserPassword(null);
//            map.put("message", "登录成功");
//            map.put("user", user);
//            session.setAttribute("user", user);
////            User user1 = (User) session.getAttribute("user");
////            System.out.println(user1.getUserName());
//        }else {
//            throw new NotFoundException(ResultEnum.USER_PASSWORS_MISTAKE);
//        }
//        return map;
    }
    @GetMapping("/logout")
    public ResponseEntity<Result> logout(HttpSession session) {
        session.removeAttribute("user");
//        map.clear();
//        map.put("message", "注销成功");
        return ResponseEntity.ok(ResultUtil.error(0, "注销成功"));
    }

    @GetMapping("/getUser/{sessionId}")
    public ResponseEntity<Result> getsession(@PathVariable String sessionId){
        MySessionContext myc= MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        User user = (User)session.getAttribute("user");
        if (user == null){
            throw new NotFoundException(ResultEnum.NOT_LOGIN);
        }
        return ResponseEntity.ok(ResultUtil.success(user));
    }

    @GetMapping("/findByUserName")
    public User findByUserName(@RequestBody User user){
        return service.findByUserName(user.getUserName());
    }

    @PostMapping("/save")
    public ResponseEntity<Result> save(@RequestBody User entity){
        return service.save(entity);
    }

    @PutMapping("/intercept/updateById/{id}")
    public ResponseEntity<Result> updateById(@PathVariable Long id, @RequestBody User newUser){
        return service.updateById(id, newUser);
    }

    @PutMapping("/intercept/updatePassword/{id}")
    public ResponseEntity<Result> updatePassword(@PathVariable Long id, @RequestBody User newUser){
        return service.updatePassword(id, newUser);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }
}
