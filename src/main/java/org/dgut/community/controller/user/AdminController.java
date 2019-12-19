package org.dgut.community.controller.user;

import org.dgut.community.service.user.impl.AdminServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAdmin")
public class AdminController {
    private AdminServiceImpl service;
}
