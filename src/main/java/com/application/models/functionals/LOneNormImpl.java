package com.application.models.functionals;

import com.application.data.Point;
import com.application.models.functionals.io.DifferentiableFunctional;
import com.application.models.functions.io.DifferentiableFunction;
import com.application.models.functions.io.Function;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;

import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.List;

public class LOneNormImpl implements DifferentiableFunctional {
    private final List<Vector> points;
    private final Vector values;

    public LOneNormImpl(@NotNull List<Vector> points, @NotNull Vector value) {
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
    public Vector gradient(Function vector) throws IllegalClassFormatException {
        if (!(vector instanceof DifferentiableFunction parameters))
            throw new IllegalClassFormatException("Неконсистентный формат функционала. Требуется DifferentiableFunctional, а получен " + vector.getClass().getName());

        int grad = parameters.gradient(this.points.getFirst()).size();
        var gradient = new VectorImpl(grad);

        for (int i = 0; i < this.points.size(); i++) {
            var value = vector.value(this.points.get(i));
            var diff = value - this.values.get(i);
            var functionGradient = parameters.gradient(this.points.get(i));

            for (int j = 0; j < grad; j++)
                gradient.set(j, Math.signum(diff) * functionGradient.get(j));
        }

        return gradient;
    }

    @Override
    public double value(Function function) {
        var sum = 0.0;

        for (int i = 0; i < this.points.size(); i++)
            sum += Math.abs(function.value(this.points.get(i)) - this.values.get(i));

        return sum;
    }
}
