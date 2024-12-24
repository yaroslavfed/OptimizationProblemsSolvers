package com.application.utilities.operations;

import com.application.data.Triple;
import com.application.models.matrices.MatrixImpl;
import com.application.models.matrices.io.Matrix;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        int n = m1.size();
        int m = m1.getFirst().size();
        int l = m2.getFirst().size();
        Matrix res = new MatrixImpl();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < l; j++) {
                res.get(i).add(0.);
                for (int k = 0; k < m; k++) {
                    double newValue = res.get(i).get(j) + m1.get(i).get(k) * m2.get(k).get(j);
                    res.get(i).set(j, newValue);
                }
            }
        }
        return res;
    }

    /**
     * Составляем матрицу из {@link Triple}
     *
     * @param valuesList сет для построения матрицы с индексами узлов
     */
    @NotNull
    private static ArrayList<ArrayList<Double>> setMatrixFromTriple(@NotNull List<Triple<Integer, Integer, Double>> valuesList) {
        int rows = valuesList.stream().mapToInt(Triple::first).max().orElse(0) + 1;
        int cols = valuesList.stream().mapToInt(Triple::second).max().orElse(0) + 1;

        // Инициализируем матрицу как список списков
        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Double> row = new ArrayList<>(Collections.nCopies(cols, 0.0));
            matrix.add(row);
        }

        // Заполняем матрицу значениями из списка тройек
        for (Triple<Integer, Integer, Double> triple : valuesList) {
            matrix.get(triple.first()).set(triple.second(), triple.third());
        }

        return matrix;
    }

    @NotNull
    public static Matrix Transpose(@NotNull Matrix mat) {
        int n = mat.size();
        int m = mat.getFirst().size();
        Matrix res = new MatrixImpl();
        for (int i = 0; i < m; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                res.get(i).add(mat.get(j).get(i));
            }
        }
        return res;
    }
}

