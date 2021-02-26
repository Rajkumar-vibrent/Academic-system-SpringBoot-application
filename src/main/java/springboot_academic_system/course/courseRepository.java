package springboot_academic_system.course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface courseRepository extends CrudRepository<databaseCourse, String> {
    Optional<databaseCourse> findById(String id);
    List<databaseCourse> findAll();
    void deleteById(String id);
}
