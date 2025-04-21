package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dto.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    User findById(int id);
    List<User> findAll();
}
