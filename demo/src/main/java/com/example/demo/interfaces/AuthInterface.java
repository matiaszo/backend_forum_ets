package com.example.demo.interfaces;

import com.example.demo.dto.auth.LoginDto;
import com.example.demo.dto.auth.LoginResponse;

public interface AuthInterface {
    LoginResponse login(LoginDto info);
}
