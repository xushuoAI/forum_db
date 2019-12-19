package org.dgut.community.controller.user;

import org.dgut.community.service.user.impl.FollowServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userFollow")
public class FollowController {
    private FollowServiceImpl service;

    public FollowController(FollowServiceImpl service) {
        this.service = service;
    }
}
