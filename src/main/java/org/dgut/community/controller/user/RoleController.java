package org.dgut.community.controller.user;

import org.dgut.community.entity.Role;
import org.dgut.community.service.user.impl.RoleServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRole")
public class RoleController {
    private RoleServiceImpl service;

    public RoleController(RoleServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Role save(@RequestBody Role entity){
        return service.save(entity);
    }
}
