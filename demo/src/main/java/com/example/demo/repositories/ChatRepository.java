package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.ChatModel;

public interface ChatRepository extends JpaRepository<ChatModel, Long> {
    public List<ChatModel> findByName(String name);

    @Query(value = "select * from tb_chat where name like '%'+ :name +'%'", nativeQuery = true)
    List<ChatModel> ShearchName(@Param("name") String name);
}
