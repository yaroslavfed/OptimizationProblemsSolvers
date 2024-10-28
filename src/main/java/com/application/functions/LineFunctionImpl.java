package com.application.functions;

import com.application.functions.io.DifferentiableFunction;
import com.application.functions.io.Function;
import com.application.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;

public class LineFunctionImpl implements ParametricFunction, DifferentiableFunction {
    @Override
    public Vector gradient(Vector point) {
        return null;
    }

    @Override
    public double value(Vector point) {
        return 0;
    }

    private record InternalLineFunction(double a, double b) implements Function {
        @Override
        public double value(@NotNull Vector point) {
            return a * point.getFirst() + b;
        }
    }

    @Override
    public Function bind(@NotNull Vector parameters) {
        return new InternalLineFunction(parameters.get(0), parameters.get(1));
    }
}
