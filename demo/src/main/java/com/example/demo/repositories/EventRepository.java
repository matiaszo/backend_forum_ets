package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.EventModel;

public interface EventRepository extends JpaRepository<EventModel, Long>
{
    @Query(value = "select * from tb_event where type = 'NEWS'", nativeQuery = true)
    List<EventModel> findNews();

    @Query(value = "select * from tb_event where type = 'EVENT'", nativeQuery = true)
    List<EventModel> findEvents();
}