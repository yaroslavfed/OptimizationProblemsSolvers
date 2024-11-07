package com.application.services.painter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PainterService {
    /**
     * Отрисовка графика из точек
     *
     * @param axisLength длина координатной прямой
     * @param points     набор точек
     */
    void paint(int axisLength, @NotNull List<Double> points) throws InterruptedException;
}
