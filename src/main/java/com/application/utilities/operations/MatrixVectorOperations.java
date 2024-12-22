package com.application.utilities.operations;

import com.application.models.matrices.io.Matrix;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * Математические операции с векторами и матрицами
 */
public class MatrixVectorOperations {
    /**
     * Перемножаем матрицу и вектор
     *
     * @param matrix входная матрица
     * @param vector входной вектор
     * @return вектор решения
     */
    @NotNull
    public static Vector multiply(@NotNull Matrix matrix, Vector vector) {
        Vector result = new VectorImpl();
        for (int i = 0; i < matrix.getN(); i++) {
            double sum = 0.0;
            for (int j = 0; j < matrix.getM(); j++) {
                sum += matrix.get(i).get(j) * vector.get(j);
            }
            result.set(i, sum);
        }
        return result;
    }
}
