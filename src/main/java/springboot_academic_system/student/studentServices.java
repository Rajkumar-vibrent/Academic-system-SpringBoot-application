package springboot_academic_system.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.course.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class studentServices {


    @Autowired
    private studentRepository studentRepo;

    ////////////// findall, findById, delete and save for student entity /////////////////////////
    public List<databaseStudent> getAllstudents(){
        List<databaseStudent> studList = new ArrayList<>();
        studentRepo.findAll().forEach(studList::add);
        return studList;
    }

    public Optional<databaseStudent> getStudentById(int Id){
        return studentRepo.findById(Id);
    }

    public void deleteStudentById(int Id){
        studentRepo.deleteById(Id);
    }

    public void add(databaseStudent student){
        studentRepo.save(student);
    }

    public List<databaseCourse> getCoursesByStudentId(int Id){
        Optional<databaseStudent> student = studentRepo.findById(Id);
        return (List<databaseCourse>) student.get().getCourses();
    }

}
