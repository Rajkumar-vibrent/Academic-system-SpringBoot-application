package springboot_academic_system.faculty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.course.courseRepository;
import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.department.databaseDepartment;
import springboot_academic_system.department.departmentRepository;
import springboot_academic_system.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class facultyServices {

    @Autowired
    facultyRepository facultyRepo;

    @Autowired
    departmentRepository departmentRepo;

    @Autowired
    courseRepository courseRepo;




    public List<databaseFaculty> getAllFaculties(){
        return (List<databaseFaculty>) facultyRepo.findAll();
    }

    public databaseFaculty getFacultyById(String faculty_id) throws Throwable{
        return facultyRepo.findById(faculty_id)
                .orElseThrow(() -> new ResourceNotFoundException("faculty id" + faculty_id + "not found"));
    }

    public void addFaculty(databaseFaculty facultyDetails){
        facultyRepo.save(facultyDetails);
    }

    public void removeFaculty(String faculty_id){
        facultyRepo.deleteById(faculty_id);
    }




    // service function for faculty - department relationship

    public void setDepartmentByFacultyId(String faculty_id, int dept_id){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        Optional<databaseDepartment> dept = departmentRepo.findById(dept_id);
        faculty.get().setDept(dept.get());
        dept.get().setFacultyList(faculty.get());
        facultyRepo.save(faculty.get());
        departmentRepo.save(dept.get());
    }

    // service function for faculty - course relationship

    public void assignCourseToFacultyById(String faculty_id, String course_id){
        Optional<databaseFaculty> faculty = facultyRepo.findById(faculty_id);
        Optional<databaseCourse> course = courseRepo.findById(course_id);
        faculty.get().setCourseSet(course.get());
        course.get().setFacultySet(faculty.get());
        facultyRepo.save(faculty.get());
        courseRepo.save(course.get());
    }
}