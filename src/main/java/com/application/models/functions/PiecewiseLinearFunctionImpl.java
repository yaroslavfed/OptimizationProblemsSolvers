package com.application.models.functions;

import com.application.data.Bounds;
import com.application.data.Point;
import com.application.models.functions.io.DifferentiableFunction;
import com.application.models.functions.io.Function;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("piecewiseLinearFunction")
public class PiecewiseLinearFunctionImpl implements ParametricFunction {
    private final Vector grid;

    public PiecewiseLinearFunctionImpl(Vector grid) {
        if (grid.size() < 2)
            throw new IllegalArgumentException("Задано слишком мало точек.");
        this.grid = grid;
    }

    @Override
    public Function bind(@NotNull Vector parameters) {
        if (parameters.isEmpty())
            throw new IllegalArgumentException("Список параметров пуст.");
        return new InternalPiecewiseLinearFunction(grid, parameters);
    }

    record InternalPiecewiseLinearFunction(Vector grid, Vector parameters) implements DifferentiableFunction {
        InternalPiecewiseLinearFunction {
            if (parameters.size() != grid.size())
                throw new IllegalArgumentException("Число параметров неверно.");
        }

        @Override
        public double value(@NotNull Vector vector) throws IllegalArgumentException {
            if (this.parameters.isEmpty())
                throw new IllegalArgumentException("Параметры не заданы.");

            if (vector.size() != 1)
                throw new IllegalArgumentException("Размерность точки должна быть равна 1.");

            try {
                var bounds = findBounds(vector.getFirst());
                return bounds.start().y()
                        + (bounds.end().y() - bounds.start().y()) / (bounds.end().x() - bounds.start().x()) * (vector.getFirst() - bounds.start().x());
            } catch (Exception exception) {
                if (vector.getFirst() < this.grid.getFirst())
                    return this.parameters.getFirst(); // Возвращаем значение в первой точке

                return this.parameters.get(this.parameters.size() - 2); // Возвращаем значение в последней точке
            }
        }

        @NotNull
        @Override
        public Vector gradient(Vector vector) throws IllegalArgumentException {
            if (this.parameters.isEmpty())
                throw new IllegalArgumentException("Параметры не заданы.");

            if (vector.size() != 1)
                throw new IllegalArgumentException("Размерность точки должна быть равна 1.");

            Vector res = new VectorImpl(this.parameters.size());

            try {
                var bounds = findBounds(vector.getFirst());
                var xDistance = bounds.end().x() - bounds.start().x();

                res.set(bounds.position() - 1, (bounds.end().x() - vector.getFirst()) / xDistance);
                res.set(bounds.position(), (vector.getFirst() - bounds.start().x()) / xDistance);

                return res;
            } catch (Exception exception) {
                if (vector.getFirst() < this.grid.getFirst()) {
                    res.set(0, 1.);
                } else {
                    res.set(res.size() - 1, 1.);
                }
                return res;
            }
        }

        @NotNull
        private Bounds findBounds(double input) {
            int p = Arrays.binarySearch(grid.toArray(), input);
            if (p < 0)
                p = ~p;
            try {
                return new Bounds(p, new Point(grid.get(p - 1), parameters.get(p - 1)), new Point(grid.get(p), parameters.get(p)));
            } catch (Exception exception) {
                throw new IllegalArgumentException(exception.getMessage() + "\nТочка находится вне заданной области.");
            }
        }
    }
}
