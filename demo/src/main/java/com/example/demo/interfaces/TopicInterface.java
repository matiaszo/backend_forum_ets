package com.example.demo.interfaces;

import com.example.demo.dto.topics.CreateTopicDTO;
import com.example.demo.dto.topics.CreateTopicFullInfoDTO;
import com.example.demo.dto.topics.TopicDTO;
public interface TopicInterface {
 
    public TopicDTO create(CreateTopicFullInfoDTO info);
    public Boolean verifyTopicTitle(String title);
    public Boolean verifyTopicInputs(CreateTopicDTO info);

    
}
