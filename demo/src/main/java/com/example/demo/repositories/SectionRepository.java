package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SectionModel;

public interface SectionRepository extends JpaRepository<SectionModel, Long>{

    public Page<SectionModel> findByTitleContainsIgnoreCase(String name, Pageable pageable);

    public List<SectionModel> findByTitle(String name);

}
