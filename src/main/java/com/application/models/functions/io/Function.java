package com.application.models.functions.io;

import com.application.models.vectors.io.Vector;

public interface Function {
    /**
     * Значение функции в точке
     *
     * @param point точка в которой ищем значение
     * @return значение в точке
     */
    double value(Vector point) throws IllegalArgumentException;
}
