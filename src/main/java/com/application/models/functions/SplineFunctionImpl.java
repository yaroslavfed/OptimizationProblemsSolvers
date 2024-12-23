package com.application.models.functions;

import com.application.data.Bounds;
import com.application.data.Point;
import com.application.data.Triple;
import com.application.models.functions.io.Function;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SplineFunctionImpl implements ParametricFunction {
    private final Vector grid;

    public SplineFunctionImpl(Vector grid) {
        if (grid.size() < 2)
            throw new IllegalArgumentException("Задано слишком мало точек.");
        this.grid = grid;
    }

    @Override
    public Function bind(@NotNull Vector parameters) {
        if (parameters.isEmpty())
            throw new IllegalArgumentException("Список параметров пуст.");
        return new InternalSplineFunction(this.grid, parameters);
    }

    record InternalSplineFunction(Vector grid, Vector parameters) implements Function {
        InternalSplineFunction {
            if (parameters.size() != grid.size() * 2)
                throw new IllegalArgumentException("Число параметров неверно.");
        }

        @Override
        public double value(Vector point) throws IllegalArgumentException {
            if (this.parameters.isEmpty())
                throw new IllegalArgumentException("Параметры не заданы.");

            if (point.size() != 1)
                throw new IllegalArgumentException("Размерность точки должна быть равна 1.");

            try {
                return getRes(point);
            } catch (Exception exception) {
                if (point.getFirst() < this.grid.getFirst())
                    return this.parameters.getFirst();

                return this.parameters.get(this.parameters.size() - 2);
            }
        }

        private double getRes(@NotNull Vector point) {
            var bounds = findBounds(point.getFirst());

            double res = 0;
            double h = bounds.first().end().x() - bounds.first().start().x();
            double ksi = (point.getFirst() - bounds.first().start().x()) / h;

            res += (1 - 3 * ksi * ksi + 2 * ksi * ksi * ksi) * bounds.first().start().y();
            res += h * (-ksi - 2 * ksi * ksi + ksi * ksi * ksi) * bounds.second();
            res += (3 * ksi * ksi - 2 * ksi * ksi * ksi) * bounds.first().end().y();
            res += h * (-ksi * ksi + ksi * ksi * ksi) * bounds.third();
            return res;
        }

        @NotNull
        private Triple<Bounds, Double, Double> findBounds(double input) {
            int p = Arrays.binarySearch(grid.toArray(), input);
            if (p < 0)
                p = ~p;
            try {
                return new Triple<>(
                        new Bounds(
                                p,
                                new Point(
                                        grid.get(p - 1),
                                        parameters.get(2 * p - 2)
                                ),
                                new Point(
                                        grid.get(p),
                                        parameters.get(2 * p)
                                )
                        ),
                        this.parameters.get(2 * p - 1),
                        this.parameters.get(2 * p + 1)
                );
            } catch (Exception exception) {
                throw new IllegalArgumentException(exception.getMessage() + "\nТочка находится вне заданной области.");
            }
        }


    }
}
