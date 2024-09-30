package com.application.functions;

import com.application.functions.io.IFunction;
import com.application.functions.io.IParametricFunction;
import com.application.models.vectors.IVector;
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
