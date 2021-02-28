package springboot_academic_system.result;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface resultRepository extends CrudRepository <databaseResult, Integer> {

}