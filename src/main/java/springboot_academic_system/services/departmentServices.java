package springboot_academic_system.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springboot_academic_system.database.databaseDepartment;
import springboot_academic_system.database.databaseCourse;
import springboot_academic_system.database.databaseFaculty;
import springboot_academic_system.repository.departmentRepository;

@Service
public class departmentServices {

    @Autowired
    departmentRepository departmentRepo;

    public void addDepartment(databaseDepartment department){
        departmentRepo.save(department);
    }

    public ResponseEntity<databaseDepartment> updateDepartmentById(String department_id, databaseDepartment deptDetails){
        Optional<databaseDepartment> department = departmentRepo.findById(department_id);
        department.get().setDepartment_name(deptDetails.getDepartment_name());
        department.get().setHod_name(deptDetails.getHod_name());
        final databaseDepartment updatedDept = departmentRepo.save(department.get());
        return ResponseEntity.ok(updatedDept);
    }

    public List<databaseFaculty> getAllFacultiesByDeptId(String dept_id){
        Optional<databaseDepartment> dept = departmentRepo.findById(dept_id);
        return dept.get().getFaculty_list();
    }

    public List<databaseCourse> getAllCourseByDeptId(String dept_id){
        Optional<databaseDepartment> dept = departmentRepo.findById(dept_id);
        return dept.get().getCourse_list();
    }

    public Optional<databaseDepartment> getDepartmentByDeptId(String dept_id){
        return departmentRepo.findById(dept_id);
    }

    public List<databaseDepartment> getAllDept(){
        return (List<databaseDepartment>) departmentRepo.findAll();
    }
}
