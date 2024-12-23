package com.application.models.functionals;

import com.application.data.Point;
import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.Function;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinfNormImpl implements Functional {
    private final List<Vector> points;
    private final Vector values;

    public LinfNormImpl(List<Vector> points, Vector value) {
        if (points.size() != value.size())
            throw new IllegalArgumentException("Различные размерности.");

        this.points = points;
        this.values = value;
    }

    @Override
    public ArrayList<Point> getPoints() {
        return null;
    }

    @Override
    public void setPoints(ArrayList<Point> points) {

    }

    @Override
    public double value(Function function) {
        var maxValue = new VectorImpl();

        for (int i = 0; i < this.points.size(); i++)
            maxValue.add(Math.abs(function.value(this.points.get(i)) - this.values.get(i)));

        return Collections.max(maxValue);
    }
}
