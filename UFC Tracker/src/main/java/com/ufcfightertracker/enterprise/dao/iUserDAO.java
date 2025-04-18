package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.User;

public interface iUserDAO {
    User save(User user);
    User findById(int id);
}
