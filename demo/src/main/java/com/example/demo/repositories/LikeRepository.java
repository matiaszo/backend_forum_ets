package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.LikeModel;


public interface LikeRepository extends JpaRepository<LikeModel, Long>
{
    @Query(value = "select l.id_like, l.id_comment, l.id_interaction from tb_like l " +
                   "inner join tb_interaction i on l.id_interaction = i.id_interaction " +
                   "where l.id_comment = :idCom and i.id_user = :idUser", nativeQuery = true)
    List<LikeModel> findByCommentAndUser(@Param("idCom") Long idCom, @Param("idUser") Long idUser);
    
    @Query(value = "select top(1) * from tb_like where id_interaction = :interaction", nativeQuery = true)
    Optional<LikeModel> findByInteraction(@Param("interaction") Long id_interaction);

    @Query(value = "select count(*) from tb_like where id_comment = :id_comment", nativeQuery = true)
    Integer countFromComment(@Param("id_comment") Long id_comment);
}
