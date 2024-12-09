package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.CommentModel;

public interface CommentRepository extends JpaRepository<CommentModel, Long>{
   @Query(value = "select * from tb_comment where id_comment != :idCom and id_topic = :idTop", nativeQuery = true)
    List<CommentModel> findByTopic(@Param("idCom") Long idCom, @Param("idTop") Long idTop);
}
