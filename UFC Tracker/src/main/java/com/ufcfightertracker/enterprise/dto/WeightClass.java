package com.ufcfightertracker.enterprise.dto;

import lombok.Data;

public @Data class WeightClass {
    private int weightClassId;
    private String weightClassName;
    private double minWeight;
    private double maxWeight;
}
