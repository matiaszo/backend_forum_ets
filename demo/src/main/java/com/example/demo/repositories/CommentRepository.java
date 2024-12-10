package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.CommentModel;

public interface CommentRepository extends JpaRepository<CommentModel, Long>{
    @Query(value = "select * from tb_comment where id_comment != :idCom and id_topic = :idTop", nativeQuery = true)
    List<CommentModel> findByTopic(@Param("idCom") Long idCom, @Param("idTop") Long idTop);

    @Query(value = "select top(1) * from tb_comment where id_interaction = :interaction", nativeQuery = true)
    Optional<CommentModel> findByInteraction(@Param("interaction") Long id_interaction);
}
