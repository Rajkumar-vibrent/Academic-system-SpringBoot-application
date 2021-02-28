package springboot_academic_system.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class studentController{

    @Autowired
    studentServices studentService;

    @RequestMapping(value = "/students")
    public List<databaseStudent> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/student/{student_id}")
    public databaseStudent getStudentById(@PathVariable String student_id) throws Throwable {
        return studentService.getStudentById(student_id);
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public void addStudent(@RequestBody databaseStudent studentDetails){
        studentService.addStudents(studentDetails);
    }

    @RequestMapping(value = "/student/remove/{student_id}", method = RequestMethod.DELETE)
    public void removeStudent(@PathVariable String student_id){
        studentService.removeStudent(student_id);
    }



    @RequestMapping(value = "/student/{student_id}/addcourse/{course_id}")
    public void addCourse(@PathVariable(value = "student_id") String student_id,
                          @PathVariable(value = "course_id") String course_id) throws Throwable{
        studentService.addCourseByStudentId(student_id, course_id);
    }

    @RequestMapping(value = "/student/{student_id}/setDept/{dept_id}")
    public void setDeptByStudentId(@PathVariable(value = "student_id") String student_id,
                                   @PathVariable(value = "dept_id") int dept_id) throws Throwable{
        studentService.setDeptByStudentId(student_id, dept_id);
    }
}