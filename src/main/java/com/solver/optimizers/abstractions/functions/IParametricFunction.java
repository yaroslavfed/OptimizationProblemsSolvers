package com.solver.optimizers.abstractions.functions;

import com.solver.optimizers.abstractions.models.IVector;

public interface IParametricFunction {
    IFunction bind(IVector parameters);
}
