package com.jmortegaf.bms.dtos;

public record SettingsDTO(
        Float importTax,
        Float generalTax,
        Float utilityFactor) {}
