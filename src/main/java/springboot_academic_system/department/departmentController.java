package springboot_academic_system.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class departmentController{

    @Autowired
    departmentServices departmentService;

    @RequestMapping(value = "/departments")
    public List<databaseDepartment> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @RequestMapping(value = "/department/{dept_id}")
    public databaseDepartment getDeptById(@PathVariable int dept_id) throws Throwable{
        return departmentService.getDeptById(dept_id);
    }

    @RequestMapping(value = "/department/add")
    public void addDepartment(@RequestBody databaseDepartment departmentDetails){
        departmentService.addDepartment(departmentDetails);
    }

    @RequestMapping(value = "/department/remove/{dept_id}")
    public void removeDepartment(@PathVariable int dept_id){
        departmentService.removeDepartment(dept_id);
    }
}