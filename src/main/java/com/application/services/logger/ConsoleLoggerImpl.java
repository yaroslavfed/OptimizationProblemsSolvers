package com.application.services.logger;

import com.application.enums.LoggerStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@Qualifier("console")
public class ConsoleLoggerImpl implements Logger {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    @Override
    public void log(LoggerStatus messageStatus, String message) {
        var time = getTime();
        var status = getStatus(messageStatus);

        System.out.printf("%s [%s] %s%n", time, status, message);
    }

    @NotNull
    private String getTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSSS"));
    }

    @NotNull
    private String getStatus(@NotNull LoggerStatus status) {
        return switch (status) {
            case TRACE -> String.format(GREEN + status + RESET);
            case INFO -> String.format(BLUE + status + RESET);
            case WARN -> String.format(YELLOW + status + RESET);
            case ERROR -> String.format(RED + status + RESET);
        };
    }
}
