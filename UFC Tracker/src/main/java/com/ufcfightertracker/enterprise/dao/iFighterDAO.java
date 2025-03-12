package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;

import java.util.List;

public interface iFighterDAO {

    Fighter save(Fighter fighter) throws Exception;

    List<Fighter> fetchAll();

    Fighter fetch(int id);

    void delete(int id);
}
