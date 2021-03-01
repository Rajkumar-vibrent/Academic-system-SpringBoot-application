package springboot_academic_system.course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCourse extends CrudRepository <DatabaseCourse, String> {

}