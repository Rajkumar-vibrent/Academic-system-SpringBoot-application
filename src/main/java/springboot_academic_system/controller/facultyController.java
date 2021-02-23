package springboot_academic_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot_academic_system.database.databaseFaculty;
import springboot_academic_system.services.facultyServices;

import java.util.List;
import java.util.Optional;

@RestController
public class facultyController {

    @Autowired
    private facultyServices faculty_service;

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


}
