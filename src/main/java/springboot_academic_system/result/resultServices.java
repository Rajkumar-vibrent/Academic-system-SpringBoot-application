package springboot_academic_system.result;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.course.courseRepository;
import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.student.databaseStudent;
import springboot_academic_system.student.studentRepository;
import springboot_academic_system.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class resultServices {

    @Autowired
    resultRepository resultRepo;

    @Autowired
    courseRepository courseRepo;

    @Autowired
    studentRepository studentRepo;



    public void addResult(databaseResult resultDetails){
        String student_name = resultDetails.getStudent_name();
        String course_name = resultDetails.getCourse_name();

        for(databaseCourse c: courseRepo.findAll()){
            if(c.getCourse_name().equals(course_name)){
                resultDetails.setCourse(c);
                break;
            }
        }

        for(databaseStudent s: studentRepo.findAll()){
            if((s.getStudent_first_name()+" "+s.getStudent_last_name()).equals(student_name)){
                resultDetails.setStudent(s);
            }
        }
        resultRepo.save(resultDetails);
    }


    public List<databaseResult> getAllResult(){
        return (List<databaseResult>) resultRepo.findAll();
    }

    public List<databaseResult> getResultByCourseId(String course_id){
        Optional<databaseCourse> course = courseRepo.findById(course_id);
        List<databaseResult> resultList = new ArrayList<>();
        for(databaseResult r: resultRepo.findAll()){
            if(r.getCourse_name().equals(course.get().getCourse_name())){
                resultList.add(r);
            }
        }
        return resultList;
    }

    public List<databaseResult> getResultByStudentId(String student_id){
        Optional<databaseStudent> student = studentRepo.findById(student_id);
        List<databaseResult> resultList = new ArrayList<>();
        for(databaseResult r: resultRepo.findAll()){
            if(r.getStudent_name().equals(student.get().getStudent_first_name() + " " + student.get().getStudent_last_name())){
                resultList.add(r);
            }
        }
        return resultList;
    }

    public void removeAllResults(){
        resultRepo.deleteAll();
    }
}