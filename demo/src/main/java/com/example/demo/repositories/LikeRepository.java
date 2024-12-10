package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.LikeModel;

public interface LikeRepository extends JpaRepository<LikeModel, Long>
{
    @Query(value = "select top(1) * from tb_like where id_interaction = :interaction", nativeQuery = true)
    Optional<LikeModel> findByInteraction(@Param("interaction") Long id_interaction);
}