package com.example.demo.interfaces;

import java.util.ArrayList;

import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.dto.section.SectionDTO;
import com.example.demo.dto.section.SectionTopicsDTO;

public interface SectionInterface {
    
    public Integer verify(CreateSectionDTO info);
    public SectionDTO createSection(CreateSectionDTO info);
    public ArrayList<SectionDTO> getSection(String name, Integer page, Integer limit);
    public SectionDTO delete(Long id);
    public SectionDTO update(Long id, CreateSectionDTO info);
    public SectionTopicsDTO getSingleSection(Long id);
    
    
    
    
}
