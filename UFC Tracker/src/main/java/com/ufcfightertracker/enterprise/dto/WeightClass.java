package com.ufcfightertracker.enterprise.dto;

import lombok.Data;

public @Data class WeightClass {
    private int weightClassId;
    private String weightClassName;
    private double minWeight;
    private double maxWeight;

    public WeightClass(int weightClassId, String weightClassName, double minWeight, double maxWeight) {
        this.weightClassId = weightClassId;
        this.weightClassName = weightClassName;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public String toString(){
        return weightClassName + ": (" + minWeight + "lbs - " + maxWeight + "lbs)";
    }
}


