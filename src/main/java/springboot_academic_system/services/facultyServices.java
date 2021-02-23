package springboot_academic_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import springboot_academic_system.database.databaseFaculty;
import springboot_academic_system.repository.facultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class facultyServices {

    @Autowired
    facultyRepository facultyRepo;

    public void addFaculty(databaseFaculty faculty){
        facultyRepo.save(faculty);
    }

    public Optional<databaseFaculty> getFaculty(String faculty_id){
        //TODO Auto-generated method later
        return facultyRepo.findById(faculty_id);
    }

    public List<databaseFaculty> getAllFaculties(){
        List<databaseFaculty> faculties = new ArrayList<>();
        facultyRepo.findAll().forEach(faculties::add);
        return faculties;
    }

    public Optional<databaseFaculty> editFacultyProfile(String faculty_id){
        return facultyRepo.findById(faculty_id);
    }

    // get all courses taught
    // get dept of faculties

}
