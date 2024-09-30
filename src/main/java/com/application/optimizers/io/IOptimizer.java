package com.application.optimizers.io;

import com.application.functionals.io.IFunctional;
import com.application.functions.io.IParametricFunction;
import com.application.models.vectors.IVector;

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
