package edu.miu.cs545.spring.repositories;

import edu.miu.cs545.spring.models.Exception;
import org.springframework.data.repository.CrudRepository;

public interface ExceptionRepository extends CrudRepository<Exception, Long> {
}
