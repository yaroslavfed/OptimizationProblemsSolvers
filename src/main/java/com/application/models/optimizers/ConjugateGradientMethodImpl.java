package com.application.models.optimizers;

import com.application.models.functionals.io.DifferentiableFunctional;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.optimizers.io.Optimizer;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import com.application.utilities.OptimizersUtilities;
import com.application.utilities.operations.VectorOperations;
import org.jetbrains.annotations.NotNull;

import java.lang.instrument.IllegalClassFormatException;

/**
 * Метод сопряжённых градиентов
 *
 * @param <TFunctional> реализуемый функционал, метод сопряжённых градиентов и метод градиентного спуска требуют DifferentiableFunctional
 * @see DifferentiableFunctional
 */
public class ConjugateGradientMethodImpl<TFunctional extends DifferentiableFunctional> implements Optimizer<TFunctional> {
    private final double eps;
    private final int maxIterations;

    public ConjugateGradientMethodImpl(double eps, int maxIterations) {

        this.eps = eps;
        this.maxIterations = maxIterations;
    }

    @Override
    public Vector minimize(TFunctional objective, ParametricFunction function, Vector initialParameters) throws IllegalClassFormatException {
        return minimize(
                objective,
                function,
                initialParameters,
                new VectorImpl(),
                new VectorImpl()
        );
    }

    @Override
    public Vector minimize(TFunctional objective, ParametricFunction function, Vector initialParameters, Vector minimumParameters) throws IllegalClassFormatException {
        return minimize(
                objective,
                function,
                initialParameters,
                minimumParameters,
                new VectorImpl()
        );
    }

    @Override
    public Vector minimize(@NotNull TFunctional objective, @NotNull ParametricFunction function, Vector initialParameters, Vector minimumParameters, Vector maximumParameters) throws IllegalClassFormatException {
        var currentParameters = VectorOperations.copyVector(initialParameters);
        var currentFunction = function.bind(currentParameters);
        var currentGradient = objective.gradient(currentFunction);
        var searchDirection = VectorOperations.multiply(-1, currentGradient);
        var currentValue = objective.value(currentFunction);

        int iteration = 0;

        while (currentValue > eps && iteration < maxIterations) {
            // Определение оптимального шага
            double alpha = OptimizersUtilities.lineSearch(objective, function, currentParameters, searchDirection);

            // Обновление параметров
            var nextParameters = VectorOperations.sum(currentParameters, VectorOperations.multiply(alpha, searchDirection));

            // Проверка границ
            if (minimumParameters != null || maximumParameters != null)
                nextParameters = OptimizersUtilities.applyConstraints(nextParameters, minimumParameters, maximumParameters);

            var nextFunction = function.bind(nextParameters);
            var nextGradient = objective.gradient(nextFunction);

            // Вычисление коэффициента beta
            double beta = VectorOperations.multiply(nextGradient, nextGradient) / VectorOperations.multiply(currentGradient, currentGradient);

            // Обновление направления поиска
            searchDirection = VectorOperations.sum(VectorOperations.multiply(-1, nextGradient), VectorOperations.multiply(beta, searchDirection));

            // Обновление текущих значений
            currentParameters = nextParameters;
            currentGradient = nextGradient;
            currentFunction = function.bind(currentParameters);
            currentValue = objective.value(currentFunction);

            iteration++;
        }

        return currentParameters;
    }
}
