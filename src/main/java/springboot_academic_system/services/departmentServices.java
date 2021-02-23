package springboot_academic_system.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import springboot_academic_system.database.databaseDepartment;
import springboot_academic_system.database.databaseCourse;
import springboot_academic_system.database.databaseFaculty;
import springboot_academic_system.repository.departmentRepository;


public class departmentServices {

    @Autowired
    departmentRepository departmentRepo;

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
