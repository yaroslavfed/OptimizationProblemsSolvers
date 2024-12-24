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
    public static Vector multiply(@NotNull Matrix matrix, @NotNull Vector vector) {
        int n = matrix.size();
        int m = vector.size();
        Vector res = new VectorImpl();
        for (int i = 0; i < n; i++) {
            res.add(0.0);
            double temp = 0.;
            for (int j = 0; j < m; j++) {
                temp += matrix.get(i).get(j) * vector.get(j);
            }
            res.set(i, temp);
        }
        return res;
    }
}
