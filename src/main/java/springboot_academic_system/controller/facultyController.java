package springboot_academic_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot_academic_system.database.databaseCourse;
import springboot_academic_system.database.databaseDepartment;
import springboot_academic_system.database.databaseFaculty;
import springboot_academic_system.services.departmentServices;
import springboot_academic_system.services.facultyServices;

import java.util.List;
import java.util.Optional;

@RestController
public class facultyController {

    @Autowired
    private facultyServices faculty_service;

    @Autowired
    private departmentServices department_service;

    @RequestMapping("/faculties")
    public List<databaseFaculty> getFaculties(){
        return faculty_service.getAllFaculties();
    }

    @RequestMapping("/faculty/{id}")
    public Optional<databaseFaculty> getFaculty(String faculty_id){
        return faculty_service.getFaculty(faculty_id);
    }

    @RequestMapping(value = "/faculty/add", method = RequestMethod.POST)
    public void addFaculty(@RequestBody databaseFaculty faculty){
        faculty_service.addFaculty(faculty);
    }

    @RequestMapping(value = "/faculty/edit/{id}", method = RequestMethod.POST)
    public void editFaculty(@RequestBody databaseFaculty faculty){
        faculty_service.editFacultyProfile(faculty);
    }

    @RequestMapping(value = "/faculty/{id}/courses")
    public List<databaseCourse> getCoursesByFacultyId(@PathVariable String id){
        List<databaseCourse> courses = (List<databaseCourse>) faculty_service.getCoursesByFacultyId(id);
        return courses;
    }

    @RequestMapping(value = "/faculty/{id}/dept")
    public String getDeptByFacultyId(@PathVariable String faculty_id){
        String dept_id = faculty_service.getDeptByFacultyId(faculty_id);
        Optional<databaseDepartment> dept = department_service.getDepartmentByDeptId(dept_id);
        return dept.get().getDepartment_name();
    }

}
