package org.dgut.community.controller.user;

import com.alibaba.fastjson.JSONObject;
import org.dgut.community.NotFoundException;
import org.dgut.community.entity.User;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.user.impl.UserServiceImpl;
import org.dgut.community.session.MySessionContext;
import org.dgut.community.util.HttpClient;
import org.dgut.community.util.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
    }
    @GetMapping("/logout")
    public ResponseEntity<Result> logout(HttpSession session) {
        session.removeAttribute("user");
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
        return ResponseEntity.ok(ResultUtil.success(service.findById(user.getUserId(), user.getUserId())));
    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<Result> findByUserId(@PathVariable Long userId, @RequestParam(defaultValue = "0") Long myId){
        User user = service.findById(userId, myId);
        return ResponseEntity.ok(ResultUtil.success(user));
    }

    @GetMapping("/findByUserName")
    public User findByUserName(User user, @RequestParam(defaultValue = "0") Long myId){
        return service.findByUserName(user.getUserName(), myId);
    }

    @GetMapping("/findByUserName2")
    public List<User> findByUserName2(User user, @RequestParam(defaultValue = "0") Long myId){
        return service.findByUserNameLike(user.getUserName(), myId);
    }

    @PostMapping("/save")
    public ResponseEntity<Result> save(@RequestBody User entity){

        //System.out.println(HttpClient.doPost(news.getNewsTitle()));
        JSONObject BaiDuC=JSONObject.parseObject(HttpClient.doPost(entity.getUserName()));

        String baiduC= (String) BaiDuC.get("conclusion");

        if (baiduC.equals("合规")){
            return  service.save(entity);
        }else{
            return ResponseEntity.ok(ResultUtil.error(9011,"昵称存在违规，包含低俗色情"));
        }
    }

    @PutMapping("/intercept/updateById/{id}")
    public ResponseEntity<Result> updateById(@PathVariable Long id, @RequestBody User newUser, HttpSession session){
        User user = (User)session.getAttribute("user");
        return service.updateById(user.getUserId(), newUser);
    }

    @PutMapping("/intercept/updatePassword/{id}")
    public ResponseEntity<Result> updatePassword(@PathVariable Long id, @RequestBody User newUser, HttpSession session){
        User user = (User)session.getAttribute("user");
        return service.updatePassword(user.getUserId(), newUser);
    }

//    @DeleteMapping("/intercept/deleteById/{id}")
//    public ResponseEntity<?> deleteById(@PathVariable Long id){
//        return service.deleteById(id);
//    }
}
