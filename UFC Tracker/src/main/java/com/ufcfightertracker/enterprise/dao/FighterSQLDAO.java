package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fighterDAO")
public class FighterSQLDAO implements iFighterDAO {
    @Autowired
    FighterRepository fighterRepository;

    @Override
    public Fighter save(Fighter fighter) throws Exception {
        Fighter createdFighter = fighterRepository.save(fighter);
        return createdFighter;
    }

    @Override
    public List<Fighter> fetchAll() {
        List<Fighter> allFighters = new ArrayList<>();
        Iterable<Fighter> fighters = fighterRepository.findAll();
        for(Fighter fighter : fighters){
            allFighters.add(fighter);
        }
        return allFighters;
    }

    @Override
    public Fighter fetch(int id) {
        return fighterRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        fighterRepository.deleteById(id);
    }

    @Override
    public Fighter fetchByName(String name) {
        return fighterRepository.findByName(name);
    }


}
