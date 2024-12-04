package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.user.UserDataDto;
import com.example.demo.interfaces.UserInterface;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.UserRepository;

public class UserService implements UserInterface {

    @Autowired
    EncoderService encoder;

    @Autowired
    UserRepository repo;

    @Override
    public void Register(UserDataDto user) {
        UserModel Usr = new UserModel();
        Usr.setEdv(user.edv());
        Usr.setName(user.name());
        Usr.setEmail(user.email());
        Usr.setPassword(encoder.encode(user.password()));
        Usr.setInstructor(user.instructor());
        Usr.setBio(null);
        Usr.setGitUsername(null);
        Usr.setImage(null);
        repo.save(Usr);
    } 

    @Override
    public Boolean validateEmail(String email) {
        List<UserModel> users = repo.findAll();

        int at = email.indexOf("@");

        if (at > 0 && at != email.length()-1)
        {
            for (int i = 0; i < users.size(); i++)
            {
                if (users.get(i).getEmail().contentEquals(email))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean validatePassword(String pass) {
        if (pass.length() >= 8) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean validateEDV(String edv) {
        var results = repo.findByEdv(edv);

        if (results.size() > 0) {
            return false;
        }

        return true;
    }
}
