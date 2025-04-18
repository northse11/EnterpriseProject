package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userDAO")
public class UserSQLDAO implements iUserDAO{
    @Autowired
    UserRepository userRepository;


    @Override
    public User save(User user) {
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }
}
