package com.example.server.repository;

import com.example.server.entity.Role;

import com.example.server.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleRepository  extends JpaRepository <Role,Integer> {


    Role findByRoleName(RoleName roleName);
    List<Role> findAllByRoleName(RoleName roleName);

    List<Role> findAllByRoleNameIn(Collection<String> roleName);
    List<Role>findAllByIdIn(Collection<Integer> id);

    Role findByRoleName(String name);
}
