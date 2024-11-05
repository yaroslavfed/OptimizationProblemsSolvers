package com.application.models.functionals.io;

import com.application.models.functions.io.Function;
import com.application.dto.Point;

import java.util.ArrayList;

public interface Functional {
    /**
     * @return точки заданной функции
     */
    ArrayList<Point> getPoints();

    /**
     * @param points точки заданной функции
     */
    void setPoints(ArrayList<Point> points);

    /**
     * Значение функционала от функции
     * @param function исходная функция
     * @return значение функционала
     */
    double value(Function function);
}