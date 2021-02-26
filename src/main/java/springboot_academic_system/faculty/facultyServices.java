package springboot_academic_system.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.department.databaseDepartment;
import springboot_academic_system.faculty.databaseFaculty;
import springboot_academic_system.course.courseRepository;
import springboot_academic_system.faculty.facultyRepository;

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
        faculty.get().addCourse(course);
        facultyRepo.save(faculty.get());
    }

    public void removeCourseByFacultyId(String course_id, String faculty_id){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        Optional<databaseCourse> course = courseRepo.findById(course_id);
        faculty.get().removeCourse(course);
        facultyRepo.save(faculty.get());
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

    public void setDepartmentByFacultyId(String faculty_id, databaseDepartment dept){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        faculty.get().addDepartment(dept);
        facultyRepo.save(faculty.get());
    }

}
