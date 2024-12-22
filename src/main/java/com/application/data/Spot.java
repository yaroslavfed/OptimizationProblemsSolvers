package com.application.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Spot<TValueData, TMarkSymbol> implements Serializable {
    //region Constants
    private static final String DEFAULT = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String TURQUOISE = "\u001B[36m";
    //endregion

    //region Fields
    private TValueData value;
    private int temperature;
    private TMarkSymbol mark;
    //endregion

    //region LifeCycle
    public Spot(@Nullable TValueData value, int temperature, TMarkSymbol mark) {
        this.value = value;
        this.temperature = temperature;
        this.mark = mark;
    }
    //endregion

    //region Properties
    public TValueData getValue() {
        return value;
    }

    public void setValue(TValueData value) {
        this.value = value;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public TMarkSymbol getMark() {
        return mark;
    }

    public void setMark(TMarkSymbol mark) {
        this.mark = mark;
    }
    //endregion

    //region Methods
    @Override
    public String toString() {
        return getTemperatureColor();
    }

    @NotNull
    private String getTemperatureColor() {
        Object obj = temperature;
        return switch (obj) {
            case Integer temp when (temp >= 50 && temp < 100) -> String.format(TURQUOISE + mark + DEFAULT);
            case Integer temp when (temp >= 100 && temp < 200) -> String.format(GREEN + mark + DEFAULT);
            case Integer temp when (temp >= 200 && temp < 400) -> String.format(YELLOW + mark + DEFAULT);
            case Integer temp when (temp >= 400) -> String.format(RED + mark + DEFAULT);
            default -> String.format(DEFAULT + mark + DEFAULT);
        };
    }
    //endregion
}
