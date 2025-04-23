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

    /**
     * Delete a fighter with a given id
     * @param id a unique identifier for each fighter
     */
    void delete(int id) throws Exception;

    /**
     * Save a fighter
     * @param fighter the fighter object passed in to be saved
     * @return the newly saved fighter
     */
    Fighter save(Fighter fighter) throws Exception;

    /**
     * Fetches all fighters
     * @return list of fighters
     */
    List<Fighter> fetchAll();

    /**
     * Fetch a fighter with given Name
     *
     * @param name the name of the fighter
     * @return the matching fighter or null if no matches found
     */
    List<Fighter> fetchByName(String name);
}
