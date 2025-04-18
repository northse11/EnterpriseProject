package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Repository
public class UserDAOStub implements iUserDAO {
    HashMap<Integer, User> allUsers = new HashMap<>();

    @Override
    public User save(User user) {
        Integer userId = user.getId();
        allUsers.put(userId, user);
        return user;
    }

    @Override
    public User findById(int id) {
        return allUsers.get(id);
    }
}
