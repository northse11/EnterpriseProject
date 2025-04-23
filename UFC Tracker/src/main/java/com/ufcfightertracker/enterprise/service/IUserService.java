package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dto.User;

import java.util.List;

public interface IUserService {
    /**
     * Saves a user object
     * @param user the user object passed in to be saved
     * @return the newly saved user
     */
    User save(User user);

    /**
     * Fetch a user with a given id
     * @param id the unique identifier given to each user
     * @return the found user
     */
    User findById(int id);

    /**
     * Fetches all users
     * @return list of users
     */
    List<User> findAll();
}
