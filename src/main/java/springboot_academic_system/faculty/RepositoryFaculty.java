package springboot_academic_system.faculty;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryFaculty extends CrudRepository <DatabaseFaculty, String> {

}