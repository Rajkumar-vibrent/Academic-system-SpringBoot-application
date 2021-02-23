package springboot_academic_system.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot_academic_system.services.studentServices;
import springboot_academic_system.database.databaseStudent;


import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

}