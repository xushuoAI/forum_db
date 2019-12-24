package org.dgut.community.service.user.impl;

import org.dgut.community.entity.Admin;
import org.dgut.community.repository.user.AdminRepository;
import org.dgut.community.service.user.IAdmin;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl implements IAdmin {
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findById(Long id) {
        return null;
    }

    @Override
    public Admin deleteById(Long id) {
        return null;
    }

    @Override
    public Admin updateById(Long id, Admin newAdmin) {
        return adminRepository.findById(id).map(admin -> {
            admin.setAdminName(newAdmin.getAdminName());
            admin.setAdminCreateTime(newAdmin.getAdminCreateTime());
            admin.setAdminEmail(newAdmin.getAdminEmail());
            admin.setAdminPhone(newAdmin.getAdminPhone());
            admin.setAdminStatus(newAdmin.getAdminStatus());
            admin.setRoleName(newAdmin.getRoleName());
            admin = adminRepository.save(admin);
            admin.setAdminPassword(null);
            return admin;
        }).orElseThrow(()-> new RuntimeException("没有该id"));
    }

    @Override
    public Admin save(Admin admin) {
        admin.setAdminPassword(DigestUtils.md5DigestAsHex(admin.getAdminPassword().getBytes()));
        admin = adminRepository.save(admin);
        admin.setAdminPassword(null);
        return admin;
    }

    @Override
    public Admin login(String name, String password) {
        return adminRepository.findByAdminNameAndAdminPassword(name, DigestUtils.md5DigestAsHex(password.getBytes()));
    }
}
