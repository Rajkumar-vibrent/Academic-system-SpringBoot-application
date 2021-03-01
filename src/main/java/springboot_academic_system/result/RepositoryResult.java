package springboot_academic_system.result;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryResult extends CrudRepository <DatabaseResult, Integer> {

}