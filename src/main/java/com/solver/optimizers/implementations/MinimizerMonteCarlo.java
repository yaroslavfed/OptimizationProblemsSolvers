package com.solver.optimizers.implementations;

import com.solver.optimizers.abstractions.functionals.IFunctional;
import com.solver.optimizers.abstractions.functions.IParametricFunction;
import com.solver.optimizers.abstractions.models.IVector;
import com.solver.optimizers.abstractions.optimizators.IOptimizer;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MinimizerMonteCarlo implements IOptimizer {
    private final int maxIters = 1000;

    @Override
    public IVector minimize(IFunctional objective, IParametricFunction function, IVector initialParameters) {
        return minimize(objective, function, initialParameters, new Vector(), new Vector());
    }

    @Override
    public IVector minimize(IFunctional objective, IParametricFunction function, IVector initialParameters, IVector minimumParameters) {
        return minimize(objective, function, initialParameters, minimumParameters, new Vector());
    }

    @Override
    public IVector minimize(@NotNull IFunctional objective,
                            @NotNull IParametricFunction function,
                            IVector initialParameters,
                            IVector minimumParameters,
                            IVector maximumParameters) {
        var param = new Vector();
        var minParam = new Vector();

        param.addAll(initialParameters);
        minParam.addAll(initialParameters);

        var fun = function.bind(param);
        var currentMin = objective.value(fun);

        var rand = new Random(0);
        for (int i = 0; i < maxIters; i++) {
            param.replaceAll(ignored -> rand.nextDouble());

            var f = objective.value(function.bind(param));
            if (f < currentMin) {
                currentMin = f;
                for (int j = 0; j < param.size(); j++)
                    minParam.set(j, param.get(j));
            }
        }
        return minParam;
    }
}
