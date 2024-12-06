package com.example.demo.interfaces;

import java.util.ArrayList;

import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.dto.section.SectionDTO;
import com.example.demo.model.SectionModel;

public interface SectionInterface {
    
    public Integer verify(CreateSectionDTO info);
    public SectionDTO createSection(CreateSectionDTO info);
    public ArrayList<SectionModel> getSpaces(String name, Integer page, Integer limit);
    
}
