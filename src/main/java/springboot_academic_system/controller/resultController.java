package springboot_academic_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import springboot_academic_system.services.resultServices;
import springboot_academic_system.database.databaseResult;
import springboot_academic_system.services.resultServices;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class resultController {

    @Autowired
    private resultServices result_service;

    @RequestMapping("/results")
    public List<databaseResult> getAllResults(){
        return result_service.getAllresults();
    }

    @RequestMapping("/result/{id}")
    public Optional<databaseResult> getResult(@PathVariable int id){
        return result_service.getResultById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/results/{id}")
    public void deleteResult(@PathVariable int id){
        result_service.deleteResultById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/results/add")
    public void addResult(@RequestBody databaseResult result){
        result_service.add(result);
    }
}
