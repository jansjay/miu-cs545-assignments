package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {
}
