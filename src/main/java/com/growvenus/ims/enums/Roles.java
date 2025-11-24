package com.growvenus.ims.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Roles {
    USER,
    ADMIN;


    @JsonCreator
    public static Roles fromValue(String value) {
        try {
            return Roles.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Role: " + value);
        }
    }
}
