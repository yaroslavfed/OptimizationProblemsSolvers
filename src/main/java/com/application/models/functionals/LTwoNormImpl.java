package com.application.models.functionals;

import com.application.data.Point;
import com.application.models.functionals.io.DifferentiableFunctional;
import com.application.models.functionals.io.LeastSquaresFunctional;
import com.application.models.functions.io.DifferentiableFunction;
import com.application.models.functions.io.Function;
import com.application.models.matrices.MatrixImpl;
import com.application.models.matrices.io.Matrix;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;

import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LTwoNormImpl implements DifferentiableFunctional, LeastSquaresFunctional {
    private final List<Vector> points;
    private final Vector values;

    public LTwoNormImpl(@NotNull List<Vector> points, @NotNull Vector value) {
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
    public Vector residual(Function function) {
        var residual = new VectorImpl();
        for (int i = 0; i < this.points.size(); i++)
            residual.add(Math.abs(function.value(this.points.get(i)) - this.values.get(i)));

        return residual;
    }

    @Override
    public Matrix jacobian(Function function) throws IllegalClassFormatException {
        if (!(function instanceof DifferentiableFunction parameters))
            throw new IllegalClassFormatException("Неконсистентный формат функционала. Требуется DifferentiableFunctional, а получен " + function.getClass().getName());
        var jacobian = new MatrixImpl();
        jacobian.addAll(this.points.stream().map(parameters::gradient).toList());

        return jacobian;
    }

    @Override
    public double value(Function function) {
        var sum = 0.0;
        var s = 0.0;
        for (int i = 0; i < this.points.size(); i++) {
            s = Math.abs(function.value(this.points.get(i)) - this.values.get(i));
            sum += s * s;
        }

        return sum;
    }

    @Override
    public Vector gradient(Function function) throws IllegalClassFormatException {
        if (!(function instanceof DifferentiableFunction parameters))
            throw new IllegalClassFormatException("Неконсистентный формат функционала. Требуется DifferentiableFunctional, а получен " + function.getClass().getName());

        int grad = parameters.gradient(this.points.getFirst()).size();
        var gradient = new VectorImpl();
        gradient.addAll(Collections.nCopies(grad, 0.0));

        for (int i = 0; i < this.points.size(); i++) {
            double value = function.value(this.points.get(i));
            double diff = value - this.values.get(i);
            var functionGradient = parameters.gradient(this.points.get(i));

            for (int j = 0; j < grad; j++)
                gradient.set(j, gradient.get(j) + 2.0 * diff * functionGradient.get(j));
        }

        return gradient;
    }
}
