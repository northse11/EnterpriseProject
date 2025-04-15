package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.WeightClass;

import java.util.List;

public interface IWeightClassService {
    /**
     * Fetch a weight class with given ID
     * @param id a unique identifier for each weight class
     * @return the matching weight class or null if no matches found
     */
    WeightClass getWeightClassById(int id);

    /**
     * Deletes a weight class with specified ID
     * @param id a unique identifier for each weight class
     * @return true if fighter deletion was successful, false if not found or failed
     */
    boolean delete(int id) throws Exception;

    /**
     * Posts provided weight class to database
     * @param weightClass data of weight to be added
     * @return weightClass data that was entered, null if unsuccessful
     */
    WeightClass save(WeightClass weightClass) throws Exception;

    /**
     * Fetches all weight classes in database
     * @return list of all fighters
     */
    List<WeightClass> fetchAll();
}
