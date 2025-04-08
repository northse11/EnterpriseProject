package com.ufcfightertracker.enterprise.service;

import com.ufcfightertracker.enterprise.dao.FighterRepository;
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
    private FighterRepository fighterRepository;

    public FighterServiceStub() {
    }

    @Override
    public Fighter fetchById(int id) {
        Fighter foundFighter = fighterRepository.findById(id).orElse(null);
        return foundFighter;
    }

    @Override
    public void delete(int id)throws Exception {
        fighterRepository.deleteById(id);
    }

    @Override
    public Fighter save(Fighter fighter)throws Exception {
        return fighterRepository.save(fighter);
    }

    @Override
    public List<Fighter> fetchAll() {
        return (List<Fighter>) fighterRepository.findAll();
    }

    @Override
    public Fighter fetchByName(String name) {
        return fighterRepository.findByName(name) ;
    }
}
