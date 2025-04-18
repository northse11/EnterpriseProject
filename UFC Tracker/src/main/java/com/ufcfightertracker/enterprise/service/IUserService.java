package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dto.User;

public interface IUserService {
    User save(User user);
    User findById(int id);
}
