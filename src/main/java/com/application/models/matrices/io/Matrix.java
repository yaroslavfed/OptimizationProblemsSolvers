package com.application.models.matrices.io;

import java.util.List;

public interface Matrix extends List<List<Double>> {
    /**
     * @return количество строк матрицы
     */
    int getN();

    /**
     * @return количество столбцов матрицы
     */
    int getM();

    String toString();
}
