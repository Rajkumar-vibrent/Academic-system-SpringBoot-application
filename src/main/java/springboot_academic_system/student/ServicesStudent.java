package springboot_academic_system.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.course.DatabaseCourse;
import springboot_academic_system.course.RepositoryCourse;
import springboot_academic_system.department.DatabaseDepartment;
import springboot_academic_system.department.RepositoryDepartment;
import springboot_academic_system.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ServicesStudent {

    @Autowired
    RepositoryStudent studentRepo;

    @Autowired
    RepositoryDepartment departmentRepo;

    @Autowired
    RepositoryCourse courseRepo;





    public List<DatabaseStudent> getAllStudents(){
        return (List<DatabaseStudent>) studentRepo.findAll();
    }

    public DatabaseStudent getStudentById(String studentId) throws Throwable {
        return (DatabaseStudent) studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student Id" + studentId + "not found"));
    }

    public void addStudents(DatabaseStudent studentDetails){
        studentRepo.save(studentDetails);
    }

    public void removeStudent(String studentId){
        studentRepo.deleteById(studentId);
    }





    public void setDeptByStudentId(String studentId, int deptId) throws Throwable{
        DatabaseStudent student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student id " + studentId + " not found"));
        DatabaseDepartment department = departmentRepo.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("department id " + deptId + " not found"));
        student.setDept(department);
        department.setStudentList(student);
        studentRepo.save(student);
        departmentRepo.save(department);
    }




    public void addCourseByStudentId(String studentId, String courseId) throws Throwable{
        DatabaseStudent student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student id " + studentId + " not found"));
        DatabaseCourse course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("course id " + courseId + " not found"));
        student.setCourseList(course);
        course.setStudentList(student);
        studentRepo.save(student);
        courseRepo.save(course);
    }

}
