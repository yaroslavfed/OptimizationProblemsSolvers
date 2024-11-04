package com.application.models.functionals;

import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.Function;
import com.application.dto.Point;
import com.application.models.vectors.VectorImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("functional")
public class FunctionalImpl implements Functional {
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
