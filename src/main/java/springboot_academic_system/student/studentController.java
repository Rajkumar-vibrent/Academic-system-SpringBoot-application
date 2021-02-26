package springboot_academic_system.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot_academic_system.course.databaseCourse;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class studentController {

    @Autowired
    private studentServices student_service;

    @RequestMapping("/students")
    public List<databaseStudent> getAllStudents() {
        return student_service.getAllstudents();
    }


    @RequestMapping("/student/{id}")
    public Optional<databaseStudent> getStudent(@PathVariable int id) {
        return student_service.getStudentById(id);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        student_service.deleteStudentById(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/students/add")
    public void addStudent(@RequestBody databaseStudent student) {
        student_service.add(student);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students/{id}/courses")
    public List<databaseCourse> getCoursesByStudentId(@PathVariable int student_id){
        return student_service.getCoursesByStudentId(student_id);
    }

}