package springboot_academic_system.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.department.RepositoryDepartment;
import springboot_academic_system.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesCourse {

    @Autowired
    RepositoryCourse courseRepo;

    @Autowired
    RepositoryDepartment departmentRepo;



    public List<DatabaseCourse> getAllCourses(){
        return (List<DatabaseCourse>) courseRepo.findAll();
    }

    public DatabaseCourse getCourseById(String courseId) throws Throwable{
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("course id " + courseId + " not found"));
    }

    public void addCourse(DatabaseCourse courseDetails){
        courseRepo.save(courseDetails);
    }

    public void removeCourse(String courseId){
        courseRepo.deleteById(courseId);
    }



    public void setDeptByCourseId(String courseId, int deptId){
        Optional<DatabaseCourse> course = courseRepo.findById(courseId);
        Optional<springboot_academic_system.department.DatabaseDepartment> dept = departmentRepo.findById(deptId);
        course.get().setDept(dept.get());
        dept.get().setCourseList(course.get());
        courseRepo.save(course.get());
        departmentRepo.save(dept.get());
    }

}