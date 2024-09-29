package com.solver.optimizers.abstractions.functionals;

import com.solver.optimizers.abstractions.functions.IFunction;
import com.solver.optimizers.domain.Point;

import java.util.ArrayList;

public interface IFunctional {
    void setPoints(ArrayList<Point> points);

    double value(IFunction function);
}