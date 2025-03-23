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
     * Deletes a fighter with specified ID
     * @param id a unique identifier for each fighter
     * @return true if fighter deletion was successful, false if not found or failed
     */
    boolean delete(int id) throws Exception;

    /**
     * Posts provided fighter to database
     * @param fighter data of fighter to be added
     * @return fighter data that was entered, null if unsuccessful
     */
    Fighter save(Fighter fighter) throws Exception;

    /**
     * Fetches all fighters in database
     * @return list of all fighters
     */
    List<Fighter> fetchAll();

    /**
     * Fetch a fighter with given Name
     * @param name the name of the fighter
     * @return the matching fighter or null if no matches found
     */
    Fighter fetchByName(String name);
}
