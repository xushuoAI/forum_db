package org.dgut.community.service.user;

import org.dgut.community.entity.Role;

public interface IRole {
    Role findById(Long id);

    Role deleteById(Long id);

    Role updateById(Long id);

    Role save(Role role);
}
