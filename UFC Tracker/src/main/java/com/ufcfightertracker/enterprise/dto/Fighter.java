package com.ufcfightertracker.enterprise.dto;

import lombok.Data;

public @Data class Fighter {
    private int id;
    private String name;
    private int age;
    private double weight;
    private String style;
    private int wins;
    private int losses;
    private int ties;
    private int rank;
}