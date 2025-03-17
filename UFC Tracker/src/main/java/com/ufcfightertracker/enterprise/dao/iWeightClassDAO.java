package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.WeightClass;

import java.util.List;

public interface iWeightClassDAO {
    WeightClass saveWeightClass(WeightClass weightClass) throws Exception;

    List<WeightClass> fetchAll();

    WeightClass getWeightClassById(int id);

    void deleteWeightClass(int id);
}
