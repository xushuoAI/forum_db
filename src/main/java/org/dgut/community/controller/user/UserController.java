package org.dgut.community.controller.user;

import org.dgut.community.service.user.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }
}
