package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Logger;
import org.springframework.data.repository.CrudRepository;

public interface LoggerRepository extends CrudRepository<Logger, Long> {
}
