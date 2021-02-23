package springboot_academic_system.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.repository.courseRepository;
import springboot_academic_system.database.databaseCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



// addCourse(databaseCourse course)
// editCourse(int course_id) return course
// updateCourse(databaseCourse course)
// getCourse(int course_id)
// List<databaseCourse> getAllCourses()
// void deleteCourse(int course_id)
// List<databaseCourse> getCoursesByStudentId(int student_id)
// List<databaseCourse> getCoursesByFacultyId(String faculty_id)
// List<databaseCourse> getCoursesByDepartmentId(String dept_id)


@Service
public class courseServices {

    @Autowired
    private courseRepository courseRepo;

    ////////////// findall, findById, delete and save for course entity /////////////////////////
    public List<databaseCourse> getAllcourses(){
        List<databaseCourse> list = new ArrayList<>();
        courseRepo.findAll().forEach(list::add);
        return list;
    }

    public Optional<databaseCourse> getCourseById(String Id){
        return courseRepo.findById(Id);
    }

    public void deleteCourseById(String Id){
        courseRepo.deleteById(Id);
    }

    public void add(databaseCourse course){
        courseRepo.save(course);
    }

}

