package com.solver.optimizers.abstractions.optimizators;

import com.solver.optimizers.abstractions.functionals.IFunctional;
import com.solver.optimizers.abstractions.functions.IParametricFunction;
import com.solver.optimizers.abstractions.models.IVector;

public interface IOptimizer {
    IVector minimize(IFunctional objective,
                     IParametricFunction function,
                     IVector initialParameters);

    IVector minimize(IFunctional objective,
                     IParametricFunction function,
                     IVector initialParameters,
                     IVector minimumParameters);

    IVector minimize(IFunctional objective,
                     IParametricFunction function,
                     IVector initialParameters,
                     IVector minimumParameters,
                     IVector maximumParameters);
}
