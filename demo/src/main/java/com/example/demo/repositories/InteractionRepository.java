package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.InteractionModel;

public interface InteractionRepository extends JpaRepository<InteractionModel, Long>
{
    @Query(
        value = "select top(10) * from tb_interaction i where i.id_user = :id_user and i.type is not null order by i.date desc",
        nativeQuery = true
    )
    List<InteractionModel> findLatest(@Param("id_user") Long id_user);
}
