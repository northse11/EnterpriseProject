package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dao.UserRepository;
import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceStub implements IUserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceStub() {
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        User foundUser = userRepository.findById(id).orElse(null);
        return foundUser;
    }
}
