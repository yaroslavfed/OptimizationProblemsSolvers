package com.application.functionals;

import com.application.functionals.io.Functional;
import com.application.functions.io.Function;
import com.application.models.Point;
import com.application.models.vectors.VectorImpl;

import java.util.ArrayList;

public class MyFunctionalImpl implements Functional {
    private ArrayList<Point> points;

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public double value(Function function) {
        double sum = 0.0;
        for (var point : points) {
            var param = new VectorImpl();
            param.add(point.x());
            var s = function.value(param) - point.y();
            sum += s * s;
        }

        return sum;
    }
}
