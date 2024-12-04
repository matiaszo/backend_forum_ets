package com.example.demo.interfaces;

import com.example.demo.dto.user.UserDataDto;

public interface UserInterface {
    void Register(UserDataDto user);

    Boolean validateEmail(String email);

    Boolean validateEDV(String edv);

    Boolean validatePassword(String pass);
}
