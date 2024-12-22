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
    public static Vector solve(Matrix m, @NotNull Vector rhs) {
        final int n = rhs.size();
        final int SolverMaxIter = 10000;
        final double SolverEps = 1e-15;

        double alpha;
        double beta;
        double squareNorm;

        Vector q = new VectorImpl(Stream.generate(() -> 0.0).limit(n).collect(Collectors.toList()));
        Vector z = new VectorImpl();
        Vector r = MatrixVectorOperations.multiply(m, q);

        for (int i = 0; i < n; i++) {
            r.set(i, rhs.get(i) - r.get(i));
            z.add(r.get(i));
        }

        Vector p = MatrixVectorOperations.multiply(m, z);
        Vector tmp;

        squareNorm = VectorOperations.multiply(r, r);

        for (int index = 0; index < SolverMaxIter && squareNorm > SolverEps; index++) {
            alpha = VectorOperations.multiply(p, r) / VectorOperations.multiply(p, p);
            for (int i = 0; i < n; i++) {
                q.set(i, alpha * z.get(i));
            }
            squareNorm = VectorOperations.multiply(r, r) - (alpha * alpha) * VectorOperations.multiply(p, p);
            for (int i = 0; i < n; i++) {
                r.set(i, alpha * p.get(i));
            }

            tmp = MatrixVectorOperations.multiply(m, r);

            beta = -VectorOperations.multiply(p, tmp) / VectorOperations.multiply(p, p);
            for (int i = 0; i < n; i++) {
                z.set(i, r.get(i) + beta * z.get(i));
                p.set(i, tmp.get(i) + beta * p.get(i));
            }
        }
        return q;
    }
}
