package springboot_academic_system.department;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDepartment extends CrudRepository <DatabaseDepartment, Integer> {

}