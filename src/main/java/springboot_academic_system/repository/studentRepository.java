package springboot_academic_system.repository;

import org.springframework.data.repository.CrudRepository;
import springboot_academic_system.database.databaseStudent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface studentRepository extends CrudRepository<databaseStudent, Integer> {
    List<databaseStudent> findAll();
    Optional<databaseStudent> findById(int Id);
    void deleteById(Integer id);
}
