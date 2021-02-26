package springboot_academic_system.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.department.databaseDepartment;
import springboot_academic_system.faculty.databaseFaculty;
import springboot_academic_system.result.databaseResult;
import springboot_academic_system.student.databaseStudent;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


// List<databaseCourse> getCoursesByStudentId(int student_id) - send to studentServices
// List<databaseCourse> getCoursesByFacultyId(String faculty_id) - send to facultyServices
// List<databaseCourse> getCoursesByDepartmentId(String dept_id) - send to departmentServices


@Service
public class courseServices {

    @Autowired
    private courseRepository courseRepo;

    @Autowired
    private EntityManager em;

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

    public void setDeptByCourseID(String course_id, databaseDepartment dept){
        Optional<databaseCourse> course = courseRepo.findById(course_id);
        course.get().setDepartment(dept);
    }

    public Set<databaseFaculty> facultiesByCourseId(String course_id){
        Optional<databaseCourse> course = courseRepo.findById(course_id);
        return course.get().getFaculties();
    }

    public Set<databaseStudent> studentsByCourseId(String course_id){
        Optional<databaseCourse> student = courseRepo.findById(course_id);
        return student.get().getStudents();
    }

    public List<databaseResult> getResultByCourseId(String course_id){
        Optional<databaseCourse> course = courseRepo.findById(course_id);
        return course.get().getResults();
    }

}

