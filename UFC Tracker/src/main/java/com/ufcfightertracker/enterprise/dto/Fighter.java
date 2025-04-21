package com.ufcfightertracker.enterprise.dto;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@Entity
@Table(name="fighters")
public class Fighter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int weightClassId;
    private String name;
    private int age;
    private double weight;
    private String style;
    private int wins;
    private int losses;
    private int ties;
    @Column(name = "`rank`")
    private int rank;

    @Version
    private int version;

    public String toString(){
        return name;
    }
}