package com.application.services.logger;

import com.application.enums.LoggerStatus;

public interface Logger {
    void log(LoggerStatus status, String message);
}
