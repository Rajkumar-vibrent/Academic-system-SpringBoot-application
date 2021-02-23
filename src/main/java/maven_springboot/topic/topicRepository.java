package maven_springboot.topic;

import org.springframework.data.repository.CrudRepository;

public interface topicRepository extends CrudRepository<topic, String> {
    //getAllTopic()
    //getTopic(String id)
    //updateTopic(Topic t)
    //deleteTopic(String id)
}
