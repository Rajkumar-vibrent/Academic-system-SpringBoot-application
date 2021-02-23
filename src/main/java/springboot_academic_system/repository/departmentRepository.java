package springboot_academic_system.repository;

import org.springframework.data.repository.CrudRepository;
import springboot_academic_system.database.databaseDepartment;

public interface departmentRepository extends CrudRepository<databaseDepartment, String> {

}
