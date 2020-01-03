package org.dgut.community.controller.user;



import org.dgut.community.entity.Admin;
import org.dgut.community.service.user.impl.AdminService;
import org.dgut.community.util.ResultArticleJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @GetMapping
    public ResultArticleJson showAllAdmin(){
        return ResultArticleJson.suc(adminService.AllList());
    }
    @GetMapping("/{id}")
    public ResultArticleJson showAdminById(@PathVariable("id") Integer id){
        return ResultArticleJson.suc(adminService.adminItem(id));
    }
    @PostMapping("/register")
    public ResultArticleJson registerAdmin(@RequestBody Admin admin){
        if (adminService.existsAdmin(admin.getAdminName())){
            return ResultArticleJson.fail(9001,"用户名已经存在");
        }else{
            return ResultArticleJson.suc(adminService.addAdmin(admin));
        }

    }

    @PostMapping("/login")
    public ResultArticleJson registerAdmin(@RequestBody Admin admin, HttpSession session){
        //存在这个用户
        if (adminService.existsAdmin(admin.getAdminName())){
            //密码正确
            if (adminService.existAccount(admin.getAdminName(),admin.getAdminPassword())){

                session.setAttribute("user",adminService.findByName(admin.getAdminName()));
                String sessId=session.getId();
                return ResultArticleJson.suc(sessId);
            }else{
                return ResultArticleJson.fail(9406,"密码错误");
            }
        }else{
            return ResultArticleJson.notFoundUser();
        }
    }
    @DeleteMapping("/{id}")
    public ResultArticleJson showNewsList(@PathVariable("id") Integer id) {
        return  ResultArticleJson.suc(adminService.deleteAdmin(id));
    }


}
