package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class FighterDAOStub implements iFighterDAO {

    HashMap<Integer, Fighter> allFighters = new HashMap<>();

    @Override
    public Fighter save(Fighter fighter) throws Exception {
        Integer fighterId = fighter.getId();
        allFighters.put(fighterId, fighter);
        return fighter;
    }

    @Override
    public List<Fighter> fetchAll() {
        List<Fighter> returnFighters = new ArrayList(allFighters.values());
        return returnFighters;
    }

    @Override
    public Fighter fetch(int id) {
        return allFighters.get(id);
    }

    @Override
    public void delete(int id) {
        allFighters.remove(id);
    }

    @Override
    public Fighter fetchByName(String name) {
        List<Fighter> fighters = new ArrayList(allFighters.values());
        for (Fighter fighter : fighters) {
            if (fighter.getName().equals(name)) {
                return fighter;
            }
        }
        return null;
    }
}
