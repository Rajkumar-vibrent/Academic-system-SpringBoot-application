package springboot_academic_system.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class courseController {

    @Autowired
    courseServices courseService;

    @RequestMapping(value = "/courses")
    public List<databaseCourse> getALlCourses(){
        return courseService.getAllCourses();
    }

    @RequestMapping(value = "/course/{course_id}")
    public databaseCourse getCourseById(@PathVariable String course_id) throws Throwable{
        return courseService.getCourseById(course_id);
    }

    @RequestMapping(value = "/course/add", method = RequestMethod.POST)
    public void addCourse(@RequestBody databaseCourse courseDetails){
        courseService.addCourse(courseDetails);
    }

    @RequestMapping(value = "/course/remove/{course_id}", method = RequestMethod.DELETE)
    public void removeCourse(@PathVariable String course_id){
        courseService.removeCourse(course_id);
    }




    @RequestMapping(value = "course/{course_id}/setDept/{dept_id}")
    public void setDeptByFacultyId(@PathVariable(value = "course_id") String course_id,
                                   @PathVariable(value = "dept_id") int dept_id) throws Throwable{
        courseService.setDeptByCourseId(course_id, dept_id);
    }

}