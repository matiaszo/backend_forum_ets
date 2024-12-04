package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserModel;
import java.util.List;


public interface UserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByEdv(String edv); 
}
