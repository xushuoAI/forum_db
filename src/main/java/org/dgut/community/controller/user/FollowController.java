package org.dgut.community.controller.user;

import org.dgut.community.entity.UserFollow;
import org.dgut.community.service.user.impl.FollowServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public UserFollow save(@RequestBody UserFollow entity){
        return service.save(entity);
    }

    @DeleteMapping("/intercept/deleteById/{followId}")
    public Map<String, Object> deleteById(@PathVariable Long followId){
        map.clear();
        map.put("message", service.deleteById(followId));
        return map;
    }

}
