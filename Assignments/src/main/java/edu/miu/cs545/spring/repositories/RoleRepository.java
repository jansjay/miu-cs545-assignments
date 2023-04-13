package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
}
