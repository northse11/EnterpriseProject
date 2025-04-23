package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FighterRepository extends CrudRepository<Fighter, Integer> {
    List<Fighter> findByName(String name);
}
