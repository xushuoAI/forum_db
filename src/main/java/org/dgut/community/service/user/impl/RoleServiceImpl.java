package org.dgut.community.service.user.impl;

import org.dgut.community.entity.Role;
import org.dgut.community.repository.user.RoleRepository;
import org.dgut.community.service.user.IRole;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRole {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public Role deleteById(Long id) {
        return null;
    }

    @Override
    public Role updateById(Long id) {
        return null;
    }

    @Override
    public Role save() {
        return null;
    }
}
