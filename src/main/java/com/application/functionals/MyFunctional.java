package com.application.functionals;

import com.application.functionals.io.IFunctional;
import com.application.functions.io.IFunction;
import com.application.models.Point;
import com.application.models.vectors.Vector;

import java.util.ArrayList;

public class MyFunctional implements IFunctional {
    private ArrayList<Point> points;

    public ArrayList<Point> getPoints() {
        return points;
    }

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
