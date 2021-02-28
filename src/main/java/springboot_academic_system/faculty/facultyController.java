package springboot_academic_system.faculty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class facultyController {

    @Autowired
    facultyServices facultyService;

    @RequestMapping(value = "/faculties")
    public List<databaseFaculty> getAllFaculties(){
        return facultyService.getAllFaculties();
    }

    @RequestMapping(value = "/faculty/{faculty_id}")
    public databaseFaculty getFacultyById(@PathVariable String faculty_id) throws Throwable{
        return facultyService.getFacultyById(faculty_id);
    }

    @RequestMapping(value = "/faculty/add", method = RequestMethod.POST)
    public void addFaculty(@RequestBody databaseFaculty facultyDetails){
        facultyService.addFaculty(facultyDetails);
    }

    @RequestMapping(value = "/faculty/remove/{faculty_id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable String faculty_id){
        facultyService.removeFaculty(faculty_id);
    }



    @RequestMapping(value = "faculty/{faculty_id}/setDept/{dept_id}")
    public void setDeptByFacultyId(@PathVariable(value = "faculty_id") String faculty_id,
                                   @PathVariable(value = "dept_id") int dept_id) throws Throwable{
        facultyService.setDepartmentByFacultyId(faculty_id, dept_id);
    }

    @RequestMapping(value = "faculty/{faculty_id}/addCourse/{course_id}")
    public void addCourseByFacultyId(@PathVariable(value = "faculty_id") String faculty_id,
                                     @PathVariable(value = "course_id") String course_id) throws Throwable{
        facultyService.assignCourseToFacultyById(faculty_id, course_id);
    }
}