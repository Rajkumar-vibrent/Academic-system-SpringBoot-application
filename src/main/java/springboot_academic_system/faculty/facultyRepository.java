package springboot_academic_system.faculty;

import org.springframework.data.repository.CrudRepository;

public interface facultyRepository extends CrudRepository<databaseFaculty, String> {

}
