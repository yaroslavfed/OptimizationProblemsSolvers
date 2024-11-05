package com.application.services.charts;

import java.io.IOException;

public interface ChartService {
    /**
     * Генератор графиков
     *
     * @return массив байт изображающий график
     */
    byte[] generateChart() throws IOException;
}
