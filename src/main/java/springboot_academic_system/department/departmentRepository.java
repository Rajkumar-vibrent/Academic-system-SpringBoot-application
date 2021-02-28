package springboot_academic_system.department;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface departmentRepository extends CrudRepository <databaseDepartment, Integer> {

}