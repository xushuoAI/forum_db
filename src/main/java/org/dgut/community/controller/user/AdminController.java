package org.dgut.community.controller.user;

import org.dgut.community.entity.Admin;
import org.dgut.community.entity.User;
import org.dgut.community.service.user.impl.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userAdmin")
public class AdminController {

    private Map<String, Object> map = new HashMap<String, Object>();
    private AdminServiceImpl service;

    public AdminController(AdminServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Admin save(@RequestBody Admin entity){
        return service.save(entity);
    }

    @PostMapping("/login")
    public Map login(String name, String password, HttpSession session){
        Admin admin = service.login(name, password);
        if (admin != null){
            admin.setAdminPassword(null);
            map.put("message", "登录成功");
            map.put("admin", admin);
            session.setAttribute("user", admin);
        }else {
            map.put("message", "用户名或密码有误");
        }
        return map;
    }

    @GetMapping("/logout")
    public Map logout(HttpSession session) {
        session.removeAttribute("admin");
        map.clear();
        map.put("message", "注销成功");
        return map;
    }

    @PutMapping("/intercept/updateById/{id}")
    public Admin updateById(@PathVariable Long id, @RequestBody Admin newAdmin){
        return service.updateById(id, newAdmin);
    }
}
