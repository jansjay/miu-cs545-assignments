package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.dto.RoleDto;

import java.util.Collection;

public interface RoleService {
    Collection<RoleDto> findAllRoles();
}
