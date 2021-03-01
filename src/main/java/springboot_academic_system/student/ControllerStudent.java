package springboot_academic_system.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerStudent{

    @Autowired
    ServicesStudent studentService;

    @RequestMapping(value = "/students")
    public List<DatabaseStudent> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/student/{studentId}")
    public DatabaseStudent getStudentById(@PathVariable String studentId) throws Throwable {
        return studentService.getStudentById(studentId);
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public void addStudent(@RequestBody DatabaseStudent studentDetails){
        studentService.addStudents(studentDetails);
    }

    @RequestMapping(value = "/student/remove/{studentId}", method = RequestMethod.DELETE)
    public void removeStudent(@PathVariable String studentId){
        studentService.removeStudent(studentId);
    }



    @RequestMapping(value = "/student/{studentId}/addcourse/{courseId}")
    public void addCourse(@PathVariable(value = "studentId") String studentId,
                          @PathVariable(value = "courseId") String courseId) throws Throwable{
        studentService.addCourseByStudentId(studentId, courseId);
    }

    @RequestMapping(value = "/student/{studentId}/setDept/{deptId}")
    public void setDeptByStudentId(@PathVariable(value = "studentId") String studentId,
                                   @PathVariable(value = "deptId") int deptId) throws Throwable{
        studentService.setDeptByStudentId(studentId, deptId);
    }
}