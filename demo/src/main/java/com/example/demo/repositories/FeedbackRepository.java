package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.FeedbackModel;

public interface FeedbackRepository extends JpaRepository<FeedbackModel, Long> {
    @Query(value = "select * from tb_feedback where id_receptor = :idUser", nativeQuery = true)
    List<FeedbackModel> findByUser(@Param("idUser") Long idUser);

    @Query(value = "select top(1) * from tb_feedback where id_interaction = :interaction", nativeQuery = true)
    Optional<FeedbackModel> findByInteraction(@Param("interaction") Long id_interaction);
}
