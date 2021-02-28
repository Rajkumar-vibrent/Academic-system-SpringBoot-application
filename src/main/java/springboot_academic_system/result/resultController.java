package springboot_academic_system.result;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class resultController {

    @Autowired
    resultServices resultService;

    @RequestMapping(value = "/results")
    public List<databaseResult> getAllResults(){
        return resultService.getAllResult();
    }

    @RequestMapping(value = "/result/byStudent/{student_id}")
    public List<databaseResult> getResultByStudentId(@PathVariable String student_id){
        return resultService.getResultByStudentId(student_id);
    }

    @RequestMapping(value = "/result/byCourse/{course_id}")
    public List<databaseResult> getResultByCourseId(@PathVariable String course_id){
        return resultService.getResultByCourseId(course_id);
    }

    @RequestMapping(value = "/result/add", method = RequestMethod.POST)
    public void addResult(@RequestBody databaseResult resultDetails){
        resultService.addResult(resultDetails);
    }

//    @RequestMapping(value = "results/remove")
//    public void removeAllResults(){
//        resultService.removeAllResults();
//    }

}