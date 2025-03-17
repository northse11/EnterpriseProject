package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.WeightClass;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class iWeightClassDAOStub implements iWeightClassDAO {

    HashMap<Integer, WeightClass> allWeightClasses = new HashMap<>();

    @Override
    public WeightClass saveWeightClass(WeightClass weightClass) throws Exception {
        Integer weightClassId = weightClass.getWeightClassId();
        allWeightClasses.put(weightClassId, weightClass);
        return weightClass;
    }

    @Override
    public List<WeightClass> fetchAll() {
        List<WeightClass> returnWeightClasses = new ArrayList(allWeightClasses.values());
        return returnWeightClasses;
    }

    @Override
    public WeightClass getWeightClassById(int id) {
        return allWeightClasses.get(id);
    }

    @Override
    public void deleteWeightClass(int id) {
        allWeightClasses.remove(id);
    }
}
