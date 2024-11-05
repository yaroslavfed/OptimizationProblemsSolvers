package com.application.enums;

public enum LoggerStatus {
    TRACE("TRACE"),
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR");

    private final String displayName;

    LoggerStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
