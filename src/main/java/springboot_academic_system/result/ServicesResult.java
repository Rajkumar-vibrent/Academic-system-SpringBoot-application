package springboot_academic_system.result;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.course.DatabaseCourse;
import springboot_academic_system.course.RepositoryCourse;
import springboot_academic_system.student.DatabaseStudent;
import springboot_academic_system.student.RepositoryStudent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesResult {

    @Autowired
    RepositoryResult resultRepo;

    @Autowired
    RepositoryCourse courseRepo;

    @Autowired
    RepositoryStudent studentRepo;



    public void addResult(String studentId, String course_id, int score){
        Optional<DatabaseStudent> student = studentRepo.findById(studentId);
        Optional<DatabaseCourse> course = courseRepo.findById(course_id);


        DatabaseResult resultDetails = new DatabaseResult();

        resultDetails.setStudent(student.get());
        resultDetails.setCourse(course.get());
        resultDetails.setScore(score);
        resultRepo.save(resultDetails);
    }


    public List<DatabaseResult> getAllResult(){
        return (List<DatabaseResult>) resultRepo.findAll();
    }

    public List<DatabaseResult> getResultByCourseId(String course_id){
        Optional<DatabaseCourse> course = courseRepo.findById(course_id);
        List<DatabaseResult> resultList = new ArrayList<>();
        for(DatabaseResult r: resultRepo.findAll()){
            if(r.getCourseName().equals(course.get().getCourseName())){
                resultList.add(r);
            }
        }
        return resultList;
    }

    public List<DatabaseResult> getResultByStudentId(String studentId){
        Optional<DatabaseStudent> student = studentRepo.findById(studentId);
        List<DatabaseResult> resultList = new ArrayList<>();
        for(DatabaseResult r: resultRepo.findAll()){
            if(r.getStudentName().equals(student.get().getStudentFirstName() + " " + student.get().getStudentLastName())){
                resultList.add(r);
            }
        }
        return resultList;
    }

    public void removeAllResults(){
        resultRepo.deleteAll();
    }
}