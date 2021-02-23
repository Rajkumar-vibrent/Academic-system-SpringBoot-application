package maven_springboot.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class topicService {

        @Autowired
        private static topicRepository TopicRepository;

//        private static List<topic> topics = new ArrayList<>(Arrays.asList(
//                new topic("spring", "spring framework", "spring description"),
//                new topic("java", "core java", "java description"),
//                new topic("javascript", "javascript", "javascript description")
//        ));

        public static List<topic> getAllTopics(){
            //return topics;
            List<topic> topics = new ArrayList<>();
            TopicRepository.findAll().forEach(topics::add);
            return topics;
        }

        public static Optional<topic> getTopic(String id){
//            return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
            return TopicRepository.findById(id);
        }

        public static void addTopic(topic Topic) {
            TopicRepository.save(Topic);
        }

//        public static void updateTopic(String id, topic Topic) {
//            for(int i=0; i<topics.size(); i++){
//                topic t = topics.get(i);
//                if(t.getId().equals(id)){
//                    topics.set(i, Topic);
//                    return;
//                }
//            }
//        }

//    public static void deleteTopic(String id){
//        topics.removeIf(t -> t.getId().equals(id));
//    }
}
