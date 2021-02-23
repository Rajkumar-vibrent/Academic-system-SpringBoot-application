package springboot_academic_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import springboot_academic_system.services.courseServices;
import springboot_academic_system.database.databaseCourse;

import java.util.List;
import java.util.Optional;

@RestController
public class courseController {

    @Autowired
    private courseServices course_service;

    @RequestMapping("/courses")
    public List<databaseCourse> getAllCourses(){
        return course_service.getAllcourses();
    }

    @RequestMapping("/course/{id}")
    public Optional<databaseCourse> getCourse(@PathVariable String id){
        return course_service.getCourseById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/courses/{id}")
    public void deleteCourse(@PathVariable String id){
        course_service.deleteCourseById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/courses/add")
    public void addCourse(@RequestBody databaseCourse course){
        course_service.add(course);
    }

}
