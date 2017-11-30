package com.loomsystems.integrations.domain.enums;

public enum IncidentOperation {
    NONE,
    UPDATE,
    CLOSE;

    public static IncidentOperation fromString(String value) {
        switch (value.toLowerCase()) {
            case "update":
                return UPDATE;
            case "close":
                return CLOSE;
                default:
                    return NONE;
        }
    }
}
