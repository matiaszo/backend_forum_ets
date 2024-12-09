package com.example.demo.interfaces;

import com.example.demo.dto.topics.CreateTopicDTO;
import com.example.demo.dto.topics.TopicDTO;
public interface TopicInterface {
 
    public TopicDTO create(CreateTopicDTO info);
    public Boolean verifyTopicTitle(String title);
    public Boolean verifyTopicInputs(CreateTopicDTO info);

    
}
