package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.User;

import java.util.List;

public interface iUserDAO {
    User save(User user);
    User findById(int id);
    List<User> findAll();
}
