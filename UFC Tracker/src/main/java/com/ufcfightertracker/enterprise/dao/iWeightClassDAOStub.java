package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.WeightClass;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class iWeightClassDAOStub implements iWeightClassDAO {

    HashMap<Integer, WeightClass> allWeightClasses = new HashMap<>();

    public iWeightClassDAOStub(){
        allWeightClasses.put(1, new WeightClass(1, "Flyweight", 116.0, 125.0));
        allWeightClasses.put(2, new WeightClass(2, "Bantamweight", 126.0, 135.0));
        allWeightClasses.put(3, new WeightClass(3, "Featherweight", 136.0, 145.0));
        allWeightClasses.put(4, new WeightClass(4, "Lightweight", 146.0, 155.0));
        allWeightClasses.put(5, new WeightClass(5, "Welterweight", 156.0, 170.0));
        allWeightClasses.put(6, new WeightClass(6, "Middleweight", 171.0, 185.0));
        allWeightClasses.put(7, new WeightClass(7, "Light Heavyweight", 186.0, 205.0));
        allWeightClasses.put(8, new WeightClass(8, "Heavyweight", 206.0, 265.0));
    }

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
