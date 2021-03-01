package springboot_academic_system.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerCourse {

    @Autowired
    ServicesCourse courseService;

    @RequestMapping(value = "/courses")
    public List<DatabaseCourse> getALlCourses(){
        return courseService.getAllCourses();
    }

    @RequestMapping(value = "/course/{courseId}")
    public DatabaseCourse getCourseById(@PathVariable String courseId) throws Throwable{
        return courseService.getCourseById(courseId);
    }

    @RequestMapping(value = "/course/add", method = RequestMethod.POST)
    public void addCourse(@RequestBody DatabaseCourse courseDetails){
        courseService.addCourse(courseDetails);
    }

    @RequestMapping(value = "/course/remove/{courseId}", method = RequestMethod.DELETE)
    public void removeCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
    }




    @RequestMapping(value = "course/{courseId}/setDept/{deptId}")
    public void setDeptByFacultyId(@PathVariable(value = "courseId") String courseId,
                                   @PathVariable(value = "deptId") int deptId) throws Throwable{
        courseService.setDeptByCourseId(courseId, deptId);
    }

}