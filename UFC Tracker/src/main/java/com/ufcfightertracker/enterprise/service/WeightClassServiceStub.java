package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dao.iWeightClassDAO;
import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.WeightClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightClassServiceStub implements IWeightClassService{

    @Autowired
    private iWeightClassDAO weightClassDAO;

    public WeightClassServiceStub() {
    }

    public WeightClassServiceStub(iWeightClassDAO weightClassDAO) {
        this.weightClassDAO = weightClassDAO;
    }

    @Override
    public WeightClass getWeightClassById(int id) {
        WeightClass foundWeightClass = weightClassDAO.getWeightClassById(id);
        return foundWeightClass;
    }

    @Override
    public void delete(int id) throws Exception {
        weightClassDAO.deleteWeightClass(id);
    }

    @Override
    public WeightClass save(WeightClass weightClass) throws Exception {
        return weightClassDAO.saveWeightClass(weightClass);
    }

    @Override
    public List<WeightClass> fetchAll() {
        return weightClassDAO.fetchAll();
    }

    @Override
    public WeightClass fetchByName(String name) {
        return null;
    }
}
