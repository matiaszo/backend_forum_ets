package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.UserSkillModel;

public interface UserSkillRepository extends JpaRepository<UserSkillModel, Long> {
    @Query(value = "select * from tb_user_skills where id_skill = :idSkill and id_user = :idUser;", nativeQuery = true)
    UserSkillModel findByUserSkill(@Param("idUser") Long idUser, @Param("idSkill") Long idSkill);
}
