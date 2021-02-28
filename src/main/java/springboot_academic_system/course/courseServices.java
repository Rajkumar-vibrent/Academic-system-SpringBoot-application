package springboot_academic_system.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.department.databaseDepartment;
import springboot_academic_system.department.departmentRepository;
import springboot_academic_system.exception.ResourceNotFoundException;
import springboot_academic_system.faculty.databaseFaculty;
import springboot_academic_system.student.databaseStudent;
import springboot_academic_system.student.studentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class courseServices {

    @Autowired
    courseRepository courseRepo;

    @Autowired
    departmentRepository departmentRepo;



    public List<databaseCourse> getAllCourses(){
        return (List<databaseCourse>) courseRepo.findAll();
    }

    public databaseCourse getCourseById(String course_id) throws Throwable{
        return courseRepo.findById(course_id)
                .orElseThrow(() -> new ResourceNotFoundException("course id " + course_id + " not found"));
    }

    public void addCourse(databaseCourse courseDetails){
        courseRepo.save(courseDetails);
    }

    public void removeCourse(String course_id){
        courseRepo.deleteById(course_id);
    }



    public void setDeptByCourseId(String course_id, int dept_id){
        Optional<databaseCourse> course = courseRepo.findById(course_id);
        Optional<databaseDepartment> dept = departmentRepo.findById(dept_id);
        course.get().setDept(dept.get());
        dept.get().setCourseList(course.get());
        courseRepo.save(course.get());
        departmentRepo.save(dept.get());
    }

}