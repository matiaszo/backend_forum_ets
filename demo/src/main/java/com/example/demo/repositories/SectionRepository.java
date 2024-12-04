package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SectionModel;

public interface SectionRepository extends JpaRepository<SectionModel, Long>{

    public List<SectionModel> findByTitleContains(String name, PageRequest req);

    public List<SectionModel> findByTitle(String name);

}
