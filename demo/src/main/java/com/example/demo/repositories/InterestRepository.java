package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.InterestModel;

public interface InterestRepository extends JpaRepository<InterestModel, Long> {
    @Query(value = "select * from tb_interest where id_user = :idUser;", nativeQuery = true)
    List<InterestModel> returnInterest(@Param("idUser") Long idUser);

    @Query(value = "select * from tb_interest where id_user = :idUser and name = :name;", nativeQuery = true)
    InterestModel findInterestUser(@Param("idUser") Long idUser, @Param("name") String nameInt);
}
