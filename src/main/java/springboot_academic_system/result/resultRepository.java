package springboot_academic_system.result;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface resultRepository extends CrudRepository<databaseResult, Integer> {
    List<databaseResult> findAll();
    Optional<databaseResult> findById(int Id);
    void deleteById(Integer id);
}