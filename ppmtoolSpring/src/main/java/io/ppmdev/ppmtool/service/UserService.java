package io.ppmdev.ppmtool.service;

import io.ppmdev.ppmtool.domain.User;
import io.ppmdev.ppmtool.exceptions.UserNameExistException;
import io.ppmdev.ppmtool.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User saveUser(User newUser){

        try{
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            return userRepo.save(newUser);
        }catch (Exception e){
            throw new UserNameExistException("User name already exist.");
        }


    }
}
