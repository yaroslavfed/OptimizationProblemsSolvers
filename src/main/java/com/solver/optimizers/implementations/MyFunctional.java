package com.solver.optimizers.implementations;

import com.solver.optimizers.abstractions.functionals.IFunctional;
import com.solver.optimizers.abstractions.functions.IFunction;
import com.solver.optimizers.domain.Point;

import java.util.ArrayList;

public class MyFunctional implements IFunctional {
    private ArrayList<Point> points;

    @Override
    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public double value(IFunction function) {
        double sum = 0.0;
        for (var point : points) {
            var param = new Vector();
            param.add(point.x());
            var s = function.value(param) - point.y();
            sum += s * s;
        }

        return sum;
    }
}
