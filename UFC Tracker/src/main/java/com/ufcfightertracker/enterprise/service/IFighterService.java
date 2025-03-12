package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dto.Fighter;

import java.util.List;

public interface IFighterService {

    /**
     * Fetch a fighter with given ID
     * @param id a unique identifier for each fighter
     * @return the matching fighter or null if no matches found
     */
    Fighter fetchById(int id);

    void delete(int id) throws Exception;

    Fighter save(Fighter fighter) throws Exception;

    List<Fighter> fetchAll();

    /**
     * Fetch a fighter with given Name
     * @param name the name of the fighter
     * @return the matching fighter or null if no matches found
     */
    Fighter fetchByName(String name);
}
