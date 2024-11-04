package com.application.models.optimizers;

import com.application.models.functions.io.Function;
import com.application.models.optimizers.io.Optimizer;
import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import com.application.models.vectors.VectorImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("monteCarloMethod")
public class MonteCarloMethodImpl implements Optimizer {
    private int maxIters = 10000;

    @Override
    public int GetMaxIterations() {
        return maxIters;
    }

    @Override
    public void SetMaxIterations(int maxIterations) {
        maxIters = maxIterations;
    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters) {
        return minimize(objective, function, initialParameters, new VectorImpl(), new VectorImpl());
    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters, Vector minimumParameters) {
        return minimize(objective, function, initialParameters, minimumParameters, new VectorImpl());
    }

    @Override
    public Vector minimize(@NotNull Functional objective,
                           @NotNull ParametricFunction function,
                           Vector initialParameters,
                           Vector minimumParameters,
                           Vector maximumParameters) {
        fillParametersList(minimumParameters, initialParameters);
        fillParametersList(maximumParameters, initialParameters);

        Vector bestParameters = initialParameters;

        Function fun = function.bind(bestParameters);
        double bestValue = objective.value(fun);

        Random random = new Random();
        for (int i = 0; i < maxIters; i++) {
            Vector candidateParameters = new VectorImpl(initialParameters.size());

            for (int j = 0; j < initialParameters.size(); j++) {
                double randomValue = minimumParameters.get(j) + (maximumParameters.get(j) - minimumParameters.get(j)) * random.nextDouble();
                candidateParameters.set(j, randomValue);
            }

            double candidateValue = objective.value(function.bind(candidateParameters));
            if (candidateValue < bestValue) {
                bestValue = candidateValue;
                bestParameters = candidateParameters;
            }
        }
        return bestParameters;
    }

    private void fillParametersList(@NotNull Vector parameters, @NotNull Vector initialParameters) {
        while (parameters.size() < initialParameters.size()) {
            parameters.add(0.0);
        }
    }
}
