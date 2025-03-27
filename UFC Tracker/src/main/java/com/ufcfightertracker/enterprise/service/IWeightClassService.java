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

    void delete(int id) throws Exception;

    WeightClass save(WeightClass weightClass) throws Exception;

    List<WeightClass> fetchAll();
}
