package com.example.demo.dto.section;

import java.util.ArrayList;

import com.example.demo.dto.topics.TopicDTO;

public record SectionTopicsDTO(SectionDTO section, ArrayList<TopicDTO> topics) {
    
}
