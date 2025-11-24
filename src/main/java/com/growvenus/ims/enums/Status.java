package com.growvenus.ims.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    OPEN, INPROGRESS, RESOLVED, CLOSED;


    @JsonCreator                 // runs before Spring converts JSON into the enum.
    public static Status fromValue(String value) {
        try {
            return Status.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Status: " + value);
        }
    }
}
