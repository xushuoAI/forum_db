package org.dgut.community.service.user.impl;

import org.dgut.community.entity.Admin;
import org.dgut.community.repository.user.AdminRepository;
import org.dgut.community.service.user.IAdmin;
import org.springframework.stereotype.Service;

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
    public Admin updateById(Long id) {
        return null;
    }

    @Override
    public Admin save() {
        return null;
    }
}
