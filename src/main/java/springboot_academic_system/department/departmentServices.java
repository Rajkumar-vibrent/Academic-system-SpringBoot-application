package springboot_academic_system.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class departmentServices{

    @Autowired
    departmentRepository departmentRepo;

    public List<databaseDepartment> getAllDepartments(){
        return (List<databaseDepartment>) departmentRepo.findAll();
    }

    public databaseDepartment getDeptById(int dept_id) throws Throwable{
        return departmentRepo.findById(dept_id)
                .orElseThrow(() -> new ResourceNotFoundException("department ID" + dept_id + "not found"));
    }

    public void addDepartment(databaseDepartment departmentDetails){
        departmentRepo.save(departmentDetails);
    }

    public void removeDepartment(int dept_id){
        departmentRepo.deleteById(dept_id);
    }

}