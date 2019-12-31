package org.dgut.community.controller.user;

import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.entity.UserFollow;
import org.dgut.community.resultenum.Result;
import org.dgut.community.service.user.impl.FollowServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userFollow")
public class FollowController {
    private FollowServiceImpl service;
    private Map<String, Object> map = new HashMap<String, Object>();

    public FollowController(FollowServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save")
    public ResponseEntity<Result> save(@RequestBody UserFollow entity, HttpSession session){
        User user = (User) session.getAttribute("user");
        entity.setFansId(user.getUserId());
        return service.save(entity);
    }

    @DeleteMapping("/intercept/deleteById")
    public ResponseEntity<?> deleteById(@RequestBody UserFollow follow, HttpSession session){
        User user = (User) session.getAttribute("user");
        follow.setFansId(user.getUserId());
        return service.deleteById(follow);
    }

    @GetMapping("/intercept/findFans/{starId}")
    public List<User> findFans(@PathVariable Long starId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByFansId(starId, pageable);
    }

    @GetMapping("/intercept/findUser/{fansId}")
    public List<User> findUser(@PathVariable Long fansId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByStarId(fansId, pageable);
    }

}
