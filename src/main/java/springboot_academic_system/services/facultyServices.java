package springboot_academic_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import springboot_academic_system.database.databaseCourse;
import springboot_academic_system.database.databaseFaculty;
import springboot_academic_system.repository.facultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public void editFacultyProfile(databaseFaculty faculty){
//        String faculty_id = faculty.getFaculty_id();
//        facultyRepo.deleteById(faculty_id);
        facultyRepo.save(faculty);
    }

    public Set<databaseCourse> getCoursseByFacultyId(String faculty_id){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        return faculty.get().getCourses();
    }

    public String getDeptByFacultyId(String faculty_id){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        return faculty.get().getFaculty_department().getDepartment_name();
    }

}
