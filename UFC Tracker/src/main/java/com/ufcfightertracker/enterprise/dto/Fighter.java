package com.ufcfightertracker.enterprise.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Represents a UFC Fighter with their attributes and statistics
 */
public @Data class Fighter {
    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Min(value = 18, message = "Fighter must be at least 18 years old")
    @Max(value = 65, message = "Fighter must be under 65 years old")
    private int age;

    @Positive(message = "Weight must be positive")
    @DecimalMax(value = "265.0", message = "Weight must not exceed 265 pounds (heavyweight limit)")
    private double weight;

    @NotBlank(message = "Fighting style is required")
    private String style;

    @PositiveOrZero(message = "Wins cannot be negative")
    private int wins;

    @PositiveOrZero(message = "Losses cannot be negative")
    private int losses;

    @PositiveOrZero(message = "Ties cannot be negative")
    private int ties;

    @Min(value = 0, message = "Rank must be 0 or higher (0 for unranked)")
    @Max(value = 15, message = "Rank must not exceed 15")
    private int rank;
}