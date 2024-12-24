package com.application.utilities.solvers;

import com.application.models.matrices.io.Matrix;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import com.application.utilities.operations.MatrixVectorOperations;
import com.application.utilities.operations.VectorOperations;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SlaeSolver {
    @NotNull
    public static Vector solve(Matrix matrix, @NotNull Vector vector) {
        final int n = vector.size();
        final int solverMaxIter = 10000;
        final double solverEps = 1e-15;

        double alpha;
        double beta;
        double squareNorm;

        Vector q = new VectorImpl(Stream.generate(() -> 0.0).limit(n).collect(Collectors.toList()));
        Vector z = new VectorImpl();
        Vector r = MatrixVectorOperations.multiply(matrix, q);

        for (int i = 0; i < n; i++) {
            r.set(i, vector.get(i) - r.get(i));
            z.add(r.get(i));
        }

        Vector p = MatrixVectorOperations.multiply(matrix, z);
        squareNorm = VectorOperations.multiply(r, r);

        Vector temp;
        for (int index = 0; index < solverMaxIter && squareNorm > solverEps; index++) {
            alpha = VectorOperations.multiply(p, r) / VectorOperations.multiply(p, p);
            for (int i = 0; i < n; i++) {
                q.set(i, q.get(i) + alpha * z.get(i));
            }
            squareNorm = VectorOperations.multiply(r, r) - (alpha * alpha) * VectorOperations.multiply(p, p);
            for (int i = 0; i < n; i++) {
                r.set(i, r.get(i) - alpha * p.get(i));
            }

            temp = MatrixVectorOperations.multiply(matrix, r);

            beta = -VectorOperations.multiply(p, temp) / VectorOperations.multiply(p, p);
            for (int i = 0; i < n; i++) {
                z.set(i, r.get(i) + beta * z.get(i));
                p.set(i, temp.get(i) + beta * p.get(i));
            }
        }
        return q;
    }
}
