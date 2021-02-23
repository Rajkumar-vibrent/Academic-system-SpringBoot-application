package springboot_academic_system.repository;

import org.springframework.data.repository.CrudRepository;
import springboot_academic_system.database.databaseFaculty;

public interface facultyRepository extends CrudRepository<databaseFaculty, String> {

}
