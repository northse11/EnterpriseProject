package com.ufcfightertracker.enterprise.dao;

import com.ufcfightertracker.enterprise.dto.Fighter;
import org.springframework.data.repository.CrudRepository;

public interface FighterRepository extends CrudRepository<Fighter, Integer> {
    Fighter findByName(String name);
}
