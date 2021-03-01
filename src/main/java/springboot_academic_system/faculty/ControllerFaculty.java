package springboot_academic_system.faculty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerFaculty {

    @Autowired
    ServicesFaculty facultyService;

    @RequestMapping(value = "/faculties")
    public List<DatabaseFaculty> getAllFaculties(){
        return facultyService.getAllFaculties();
    }

    @RequestMapping(value = "/faculty/{facultyId}")
    public DatabaseFaculty getFacultyById(@PathVariable String facultyId) throws Throwable{
        return facultyService.getFacultyById(facultyId);
    }

    @RequestMapping(value = "/faculty/add", method = RequestMethod.POST)
    public void addFaculty(@RequestBody DatabaseFaculty facultyDetails){
        facultyService.addFaculty(facultyDetails);
    }

    @RequestMapping(value = "/faculty/remove/{facultyId}", method = RequestMethod.DELETE)
    public void remove(@PathVariable String facultyId){
        facultyService.removeFaculty(facultyId);
    }



    @RequestMapping(value = "faculty/{facultyId}/setDept/{deptId}")
    public void setDeptByFacultyId(@PathVariable(value = "facultyId") String facultyId,
                                   @PathVariable(value = "deptId") int deptId) throws Throwable{
        facultyService.setDepartmentByFacultyId(facultyId, deptId);
    }

    @RequestMapping(value = "faculty/{facultyId}/addCourse/{course_id}")
    public void addCourseByFacultyId(@PathVariable(value = "facultyId") String facultyId,
                                     @PathVariable(value = "course_id") String course_id) throws Throwable{
        facultyService.assignCourseToFacultyById(facultyId, course_id);
    }
}