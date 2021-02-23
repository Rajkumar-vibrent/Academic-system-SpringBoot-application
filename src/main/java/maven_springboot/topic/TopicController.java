package maven_springboot.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TopicController {

    @Autowired
    private topicService TopicService;

    @RequestMapping("/topics")  //the default function performed is get() so no specification required.
    public List<topic> getAllTopics(){
        //spring mvc helps converting this data of list of topic to json automatically
        return topicService.getAllTopics();
    }

    @RequestMapping("/topics/{id}")
    public Optional<topic> getTopic(@PathVariable String id){
        return topicService.getTopic(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public void addTopic(@RequestBody topic Topic){
        topicService.addTopic(Topic);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
//    public void updateTopic(@RequestBody topic Topic, @PathVariable String id){
//        topicService.updateTopic(id, Topic);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
//    public void deleteTopic(@PathVariable String id){
//        topicService.deleteTopic(id);
//    }

}
