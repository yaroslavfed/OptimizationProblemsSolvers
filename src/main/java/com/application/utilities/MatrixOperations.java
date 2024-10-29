package com.application.utilities;

import com.application.models.matrices.MatrixImpl;
import com.application.models.matrices.io.Matrix;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Математические операции с матрицами
 */
public class MatrixOperations {
    /**
     * Умножение матрицы на матрицу
     *
     * @param m1 первая матрица
     * @param m2 вторая матрица
     * @return матрицу - результат перемножения матриц
     */
    @NotNull
    public static Matrix multiply(@NotNull Matrix m1, @NotNull Matrix m2) {
        if (m1.getM() != m2.getN())
            throw new IllegalArgumentException("Matrices have different dimensions");

        List<Triple<Integer, Integer, Double>> valuesList = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0, m1.getN())
                .parallel()
                .forEach(i -> {
                    for (int j = 0; j < m1.getM(); j++) {
                        double sum = 0.0;
                        for (int k = 0; k < m2.getM(); k++) {
                            sum += m1.get(i).get(k) * m2.get(k).get(j);
                        }
                        valuesList.add(new Triple<>(i, j, sum));
                    }
                });
        valuesList.sort(Comparator.comparing(Triple<Integer, Integer, Double>::first).thenComparing(Triple::second));

        return new MatrixImpl(setMatrixFromTriple(valuesList));
    }

    /**
     * Составляем матрицу из {@link Triple}
     *
     * @param valuesList сет для построения матрицы с индексами узлов
     */
    @NotNull
    private static ArrayList<List<Double>> setMatrixFromTriple(@NotNull List<Triple<Integer, Integer, Double>> valuesList) {
        var rows = 0;
        var cols = 0;

        for (Triple<Integer, Integer, Double> triple : valuesList) {
            rows = Math.max(rows, triple.first() + 1);
            cols = Math.max(cols, triple.second() + 1);
        }

        ArrayList<List<Double>> matrix = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<Double> row = new ArrayList<>(cols);
            for (int j = 0; j < cols; j++) {
                row.add(0.0);
            }
            matrix.add(row);
        }

        for (Triple<Integer, Integer, Double> triple : valuesList) {
            int i = triple.first();
            int j = triple.second();
            double value = triple.third();
            matrix.get(i).set(j, value);
        }

        return matrix;
    }
}

