package com.example.demo.interfaces;

import com.example.demo.dto.user.UserDataDto;
import com.example.demo.model.UserModel;
import java.util.List;

public interface UserInterface {
    void Register(UserDataDto user);

    Boolean validateEmail(String email);

    Boolean validateEDV(String edv);

    Boolean validatePassword(String pass);

    Boolean validateUser(Long id);

    List<UserModel> getUsers(String name);
}
