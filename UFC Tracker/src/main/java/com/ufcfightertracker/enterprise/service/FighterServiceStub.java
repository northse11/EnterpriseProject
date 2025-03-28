package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dao.iFighterDAO;
import com.ufcfightertracker.enterprise.dto.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FighterServiceStub implements IFighterService {

    @Autowired
    private iFighterDAO fighterDAO;

    public FighterServiceStub() {
    }

    public FighterServiceStub(iFighterDAO fighterDAO) {
        this.fighterDAO = fighterDAO;
    }

    @Override
    public Fighter fetchById(int id) {
        Fighter foundFighter = fighterDAO.fetch(id);
        return foundFighter;
    }

    @Override
    public void delete(int id)throws Exception {
        fighterDAO.delete(id);
    }

    @Override
    public Fighter save(Fighter fighter)throws Exception {
        return fighterDAO.save(fighter);
    }

    @Override
    public List<Fighter> fetchAll() {
        return fighterDAO.fetchAll();
    }

    @Override
    public Fighter fetchByName(String name) {
        return fighterDAO.fetchByName(name);
    }
}
