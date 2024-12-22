package com.application.models.optimizers;

import com.application.models.functionals.io.LeastSquaresFunctional;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import com.application.models.optimizers.io.Optimizer;
import com.application.utilities.operations.MatrixOperations;
import com.application.utilities.operations.MatrixVectorOperations;
import com.application.utilities.OptimizersUtilities;
import com.application.utilities.operations.VectorOperations;
import com.application.utilities.solvers.SlaeSolver;
import org.jetbrains.annotations.NotNull;

public class GaussNewtonAlgorithmImpl<TFunctional extends LeastSquaresFunctional> implements Optimizer<TFunctional> {
    private final double eps;
    private final int maxIterations;

    public GaussNewtonAlgorithmImpl(double eps, int maxIterations) {
        this.eps = eps;
        this.maxIterations = maxIterations;
    }

    @Override
    public Vector minimize(TFunctional objective, ParametricFunction function, Vector initialParameters) {
        return minimize(
                objective,
                function,
                initialParameters,
                new VectorImpl(),
                new VectorImpl()
        );
    }

    @Override
    public Vector minimize(TFunctional objective, ParametricFunction function, Vector initialParameters, Vector minimumParameters) {
        return minimize(
                objective,
                function,
                initialParameters,
                minimumParameters,
                new VectorImpl()
        );
    }

    @Override
    public Vector minimize(@NotNull TFunctional objective, @NotNull ParametricFunction function, Vector initialParameters, Vector minimumParameters, Vector maximumParameters) {
        var currentParameters = VectorOperations.copyVector(initialParameters);
        var currentFunction = function.bind(currentParameters);
        var currentValue = objective.value(currentFunction);
        var residual = objective.residual(currentFunction);

        int iteration = 0;
        while (iteration < maxIterations && currentValue > eps) {
            var jacobian = objective.jacobian(currentFunction);
            var jacobianTranspose = MatrixOperations.Transpose(jacobian);

            // Решение системы J^T * J * delta = -J^T * r*
            var gradient = MatrixVectorOperations.multiply(jacobianTranspose, residual);
            var hessian = MatrixOperations.multiply(jacobianTranspose, jacobian);
            var delta = SlaeSolver.solve(hessian, VectorOperations.multiply(-1, gradient));

            // Обновление параметров
            var nextParameters = VectorOperations.subtract(currentParameters, delta);

            // Проверка границ
            if (minimumParameters != null || maximumParameters != null) {
                nextParameters = OptimizersUtilities.applyConstraints(nextParameters, minimumParameters, maximumParameters);
            }

            currentParameters = nextParameters;
            currentFunction = function.bind(currentParameters);
            currentValue = objective.value(currentFunction);
            iteration++;
        }

        return currentParameters;
    }
}
