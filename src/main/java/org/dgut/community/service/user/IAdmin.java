package org.dgut.community.service.user;

import org.dgut.community.entity.Admin;

public interface IAdmin {
    Admin findById(Long id);

    Admin deleteById(Long id);

    Admin updateById(Long id, Admin admin);

    Admin save(Admin admin);

    Admin login(String name, String password);
}
