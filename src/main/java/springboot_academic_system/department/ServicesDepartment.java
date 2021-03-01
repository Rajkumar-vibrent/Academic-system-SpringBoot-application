package springboot_academic_system.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ServicesDepartment {

    @Autowired
    RepositoryDepartment departmentRepo;

    public List<DatabaseDepartment> getAllDepartments(){
        return (List<DatabaseDepartment>) departmentRepo.findAll();
    }

    public DatabaseDepartment getDeptById(int deptId) throws Throwable{
        return departmentRepo.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("department ID" + deptId + "not found"));
    }

    public void addDepartment(DatabaseDepartment departmentDetails){
        departmentRepo.save(departmentDetails);
    }

    public void removeDepartment(int deptId){
        departmentRepo.deleteById(deptId);
    }

}