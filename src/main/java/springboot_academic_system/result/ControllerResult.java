package springboot_academic_system.result;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerResult {

    @Autowired
    ServicesResult resultService;

    @RequestMapping(value = "/results")
    public List<DatabaseResult> getAllResults(){
        return resultService.getAllResult();
    }

    @RequestMapping(value = "/result/byStudent/{student_id}")
    public List<DatabaseResult> getResultByStudentId(@PathVariable String student_id){
        return resultService.getResultByStudentId(student_id);
    }

    @RequestMapping(value = "/result/byCourse/{course_id}")
    public List<DatabaseResult> getResultByCourseId(@PathVariable String course_id){
        return resultService.getResultByCourseId(course_id);
    }

    @RequestMapping(value = "/result/add/{student_id}/{course_id}/{score}", method = RequestMethod.POST)
    public void addResult(@PathVariable(value = "student_id") String student_id,
                          @PathVariable(value = "course_id") String course_id,
                          @PathVariable(value = "score") int score){
        resultService.addResult(student_id, course_id, score);
    }

//    @RequestMapping(value = "results/remove")
//    public void removeAllResults(){
//        resultService.removeAllResults();
//    }

}