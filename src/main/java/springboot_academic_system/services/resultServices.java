package springboot_academic_system.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_academic_system.repository.resultRepository;
import springboot_academic_system.database.databaseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class resultServices {

    @Autowired
    private resultRepository resultRepo;


    ////////////// findall, findById, delete and save for result entity /////////////////////////
    public List<databaseResult> getAllresults(){
        List<databaseResult> resultList = new ArrayList<>();
        resultRepo.findAll().forEach(resultList::add);
        return resultList;
    }

    public Optional<databaseResult> getResultById(int Id){
        return resultRepo.findById(Id);
    }

    public void deleteResultById(int Id){
        resultRepo.deleteById(Id);
    }

    public void add(databaseResult result){
        resultRepo.save(result);
    }

}
