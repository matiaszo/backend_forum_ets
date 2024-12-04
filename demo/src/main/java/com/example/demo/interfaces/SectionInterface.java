package com.example.demo.interfaces;

import java.util.ArrayList;

import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.model.SectionModel;

public interface SectionInterface {
    
    public Integer createSection(CreateSectionDTO name, Long id);
    public ArrayList<SectionModel> getSpaces(String name, Integer page, Integer limit);
    
}
