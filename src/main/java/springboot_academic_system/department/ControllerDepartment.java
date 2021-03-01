package springboot_academic_system.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot_academic_system.course.DatabaseCourse;

import java.util.List;

@RestController
public class ControllerDepartment {

    @Autowired
    ServicesDepartment departmentService;

    @RequestMapping(value = "/departments")
    public List<DatabaseDepartment> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @RequestMapping(value = "/department/{deptId}")
    public DatabaseDepartment getDeptById(@PathVariable int deptId) throws Throwable{
        return departmentService.getDeptById(deptId);
    }

    @RequestMapping(value = "/department/add")
    public void addDepartment(@RequestBody DatabaseDepartment departmentDetails){
        departmentService.addDepartment(departmentDetails);
    }

    @RequestMapping(value = "/department/remove/{deptId}")
    public void removeDepartment(@PathVariable int deptId){
        departmentService.removeDepartment(deptId);
    }
}