package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    @Query(value = "select * from tb_message where id_chat = :chat", nativeQuery = true)
    List<MessageModel> findByChat(@Param("chat") Long chat);
}
