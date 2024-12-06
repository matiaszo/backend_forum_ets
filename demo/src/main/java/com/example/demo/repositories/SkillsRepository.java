package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.skills.SkillDataDto;
import com.example.demo.model.SkillsModel;

public interface SkillsRepository extends JpaRepository<SkillsModel, Long>{
    List<SkillsModel> findByName(String name); 

    @Query(value = "select S.name, S.image from tb_skills S inner join tb_user_skills US on S.id_skills = US.id_skill inner join tb_user U on US.id_user = U.id_user where U.id_user = :idUser", nativeQuery = true)
    List<SkillDataDto> queryByUser(@Param("idUser") Long idUser);

    @Query(value = "select S.id_skills, S.name, S.image from tb_skills S inner join tb_user_skills US on S.id_skills = US.id_skill inner join tb_user U on US.id_user = U.id_user where U.id_user = :idUser", nativeQuery = true)
    List<Object[]> returnSkillDto(@Param("idUser") Long idUser);
}
