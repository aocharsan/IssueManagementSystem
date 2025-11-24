package com.growvenus.ims.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Priority {
    LOW, MEDIUM, HIGH;

    @JsonCreator                 // runs before Spring converts JSON into the enum.
    public static Priority fromValue(String value) {
        try {
            return Priority.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Priority: " + value);
        }
    }
}
