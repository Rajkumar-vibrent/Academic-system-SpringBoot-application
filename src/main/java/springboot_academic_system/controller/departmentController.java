package springboot_academic_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springboot_academic_system.database.databaseCourse;
import springboot_academic_system.database.databaseDepartment;
import springboot_academic_system.database.databaseFaculty;
import springboot_academic_system.services.departmentServices;

import java.util.List;
import java.util.Optional;

@RestController
public class departmentController {

    @Autowired
    private departmentServices department_service;

    @RequestMapping(value = "/departments")
    public List<databaseDepartment> getAllDepts(){
        return department_service.getAllDept();
    }

    @RequestMapping(value = "/departments/add", method = RequestMethod.POST)
    public void addDepartment(@RequestBody databaseDepartment department){
        department_service.addDepartment(department);
    }

    @RequestMapping(value = "/departments/update/{dept_id}", method = RequestMethod.PUT)
    public ResponseEntity<databaseDepartment> updateDepartment(@PathVariable(value = "dept_id") String department_id,
                                                               @Validated @RequestBody databaseDepartment deptDetails){
        return department_service.updateDepartmentById(department_id, deptDetails);
    }

    @RequestMapping(value = "/department/{dept_id}")
    public Optional<databaseDepartment> getDeptById(@PathVariable String dept_id){
        return department_service.getDepartmentByDeptId(dept_id);
    }

    @RequestMapping(value = "/department/{dept_id}/faculties")
    public List<databaseFaculty> getFacultiesByDeptId(@PathVariable String dept_id){
        return department_service.getAllFacultiesByDeptId(dept_id);
    }

    @RequestMapping(value = "/department/{dept_id}/courses")
    public List<databaseCourse> getCoursesByDeptId(@PathVariable String dept_id){
        return department_service.getAllCourseByDeptId(dept_id);
    }


 }
