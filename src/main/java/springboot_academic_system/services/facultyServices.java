package springboot_academic_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.database.databaseCourse;
import springboot_academic_system.database.databaseFaculty;
import springboot_academic_system.repository.courseRepository;
import springboot_academic_system.repository.facultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class facultyServices {

    @Autowired
    facultyRepository facultyRepo;

    @Autowired
    courseRepository courseRepo;

    public void addFaculty(databaseFaculty faculty){
        facultyRepo.save(faculty);
    }

    public void addCourseByFacultyId(String course_id, String faculty_id){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        Optional<databaseCourse> course = courseRepo.findById(course_id);
        faculty.get().getCourses().add(course.get());
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

    public Set<databaseCourse> getCoursesByFacultyId(String faculty_id){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        return faculty.get().getCourses();
    }

    public String getDeptByFacultyId(String faculty_id){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        return faculty.get().getFaculty_department().getDepartment_name();
    }

}
