package springboot_academic_system.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.course.courseRepository;
import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.department.databaseDepartment;
import springboot_academic_system.department.departmentRepository;
import springboot_academic_system.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

@Service
public class studentServices {

    @Autowired
    studentRepository studentRepo;

    @Autowired
    departmentRepository departmentRepo;

    @Autowired
    courseRepository courseRepo;





    public List<databaseStudent> getAllStudents(){
        return (List<databaseStudent>) studentRepo.findAll();
    }

    public databaseStudent getStudentById(String student_id) throws Throwable {
        return (databaseStudent) studentRepo.findById(student_id)
                .orElseThrow(() -> new ResourceNotFoundException("student Id" + student_id + "not found"));
    }

    public void addStudents(databaseStudent studentDetails){
        studentRepo.save(studentDetails);
    }

    public void removeStudent(String student_id){
        studentRepo.deleteById(student_id);
    }





    public void setDeptByStudentId(String student_id, int dept_id) throws Throwable{
        databaseStudent student = studentRepo.findById(student_id)
                .orElseThrow(() -> new ResourceNotFoundException("student id " + student_id + " not found"));
        databaseDepartment department = departmentRepo.findById(dept_id)
                .orElseThrow(() -> new ResourceNotFoundException("department id " + dept_id + " not found"));
        student.setDept(department);
        department.setStudentList(student);
        studentRepo.save(student);
        departmentRepo.save(department);
    }




    public void addCourseByStudentId(String student_id, String course_id) throws Throwable{
        databaseStudent student = studentRepo.findById(student_id)
                .orElseThrow(() -> new ResourceNotFoundException("student id " + student_id + " not found"));
        databaseCourse course = courseRepo.findById(course_id)
                .orElseThrow(() -> new ResourceNotFoundException("course id " + course_id + " not found"));
        student.setCourseList(course);
        course.setStudentList(student);
        studentRepo.save(student);
        courseRepo.save(course);
    }

}
