package springboot_academic_system.faculty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.course.DatabaseCourse;
import springboot_academic_system.course.RepositoryCourse;
import springboot_academic_system.department.RepositoryDepartment;
import springboot_academic_system.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesFaculty {

    @Autowired
    RepositoryFaculty facultyRepo;

    @Autowired
    RepositoryDepartment departmentRepo;

    @Autowired
    RepositoryCourse courseRepo;




    public List<DatabaseFaculty> getAllFaculties(){
        return (List<DatabaseFaculty>) facultyRepo.findAll();
    }

    public DatabaseFaculty getFacultyById(String facultyId) throws Throwable{
        return facultyRepo.findById(facultyId)
                .orElseThrow(() -> new ResourceNotFoundException("faculty id" + facultyId + "not found"));
    }

    public void addFaculty(DatabaseFaculty facultyDetails){
        facultyRepo.save(facultyDetails);
    }

    public void removeFaculty(String facultyId){
        facultyRepo.deleteById(facultyId);
    }




    // service function for faculty - department relationship

    public void setDepartmentByFacultyId(String facultyId, int deptId){
        Optional<DatabaseFaculty> faculty = facultyRepo.findById(facultyId);
        Optional<springboot_academic_system.department.DatabaseDepartment> dept = departmentRepo.findById(deptId);
        faculty.get().setDept(dept.get());
        dept.get().setFacultyList(faculty.get());
        facultyRepo.save(faculty.get());
        departmentRepo.save(dept.get());
    }

    // service function for faculty - course relationship

    public void assignCourseToFacultyById(String facultyId, String course_id){
        Optional<DatabaseFaculty> faculty = facultyRepo.findById(facultyId);
        Optional<DatabaseCourse> course = courseRepo.findById(course_id);
        faculty.get().setCourseSet(course.get());
        course.get().setFacultySet(faculty.get());
        facultyRepo.save(faculty.get());
        courseRepo.save(course.get());
    }
}