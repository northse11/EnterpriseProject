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
     * Delete a weight class with a given ID
     * @param id a unique identifier for each weight class
     */
    void delete(int id) throws Exception;

    /**
     * Saves a weight class
     * @param weightClass the object data passed in to be saved
     * @return the newly saved weight class object
     */
    WeightClass save(WeightClass weightClass) throws Exception;

    /**
     * Fetches all weight classes
     * @return list of weight classes
     */
    List<WeightClass> fetchAll();
}
