package com.solver.optimizers.abstractions.functions;

import com.solver.optimizers.abstractions.models.IVector;

public interface IDifferentiableFunction extends IFunction {
    IVector gradient(IVector point);
}
