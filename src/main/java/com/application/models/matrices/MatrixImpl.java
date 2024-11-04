package com.application.models.matrices;

import com.application.models.matrices.io.Matrix;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@Component
public class MatrixImpl extends ArrayList<List<Double>> implements Matrix {
    private final int n;
    private final int m;

    /**
     * Создает пустую матрицу
     */
    public MatrixImpl() {
        super();

        this.n = 0;
        this.m = 0;
    }

    /**
     * Создает нулевую матрицу заданой размерности
     *
     * @param n количество строк в матрице
     * @param m количество столбцов в матрице
     */
    public MatrixImpl(int n, int m) {
        super(m);

        this.n = n;
        this.m = m;

        for (int i = 0; i < n; i++) {
            List<Double> matrixLine = new ArrayList<>();
            for (int j = 0; j < m; j++)
                matrixLine.add(0.0);
            this.add(matrixLine);
        }
    }

    /**
     * Создает заполненную матрицу из элементов списка
     *
     * @param baseMatrix базовая матрица из списка списков
     */
    public MatrixImpl(@NotNull List<List<Double>> baseMatrix) {
        super(baseMatrix.size());

        this.n = baseMatrix.size();
        this.m = baseMatrix.getFirst().size();

        for (int i = 0; i < n; i++) {
            List<Double> matrixLine = new ArrayList<>();
            for (int j = 0; j < m; j++)
                matrixLine.add(baseMatrix.get(i).get(j));
            this.add(matrixLine);
        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        IntStream.range(0, n).forEach(nIndex -> {
            result.append("|");
            IntStream.range(0, m).forEach(mIndex ->
                    result.append(this.get(nIndex).get(mIndex)).append(" "));
            result.replace(result.length() - 1, result.length(), "|\n");
        });
        return result.toString();
    }
}
