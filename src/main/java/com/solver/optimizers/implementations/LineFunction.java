package com.solver.optimizers.implementations;

import com.solver.optimizers.abstractions.functions.IFunction;
import com.solver.optimizers.abstractions.functions.IParametricFunction;
import com.solver.optimizers.abstractions.models.IVector;
import org.jetbrains.annotations.NotNull;

public class LineFunction implements IParametricFunction {
    private record InternalLineFunction(double a, double b) implements IFunction {
        @Override
        public double value(@NotNull IVector point) {
            return a * point.getFirst() + b;
        }
    }

    @Override
    public IFunction bind(@NotNull IVector parameters) {
        return new InternalLineFunction(parameters.get(0), parameters.get(1));
    }
}
