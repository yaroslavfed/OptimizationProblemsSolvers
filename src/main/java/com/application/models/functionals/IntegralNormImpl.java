package com.application.models.functionals;

import com.application.data.Point;
import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.Function;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class IntegralNormImpl implements Functional {
    private final Vector a;
    private final Vector b;

    public IntegralNormImpl(@NotNull Vector a, @NotNull Vector b) {
        if (a.size() != b.size())
            throw new IllegalArgumentException("Различные размерности.");

        this.a = a;
        this.b = b;
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
        double res = 0.0;
        double volume = 1.0;
        int dim = this.b.size();

        double[] gaussNodes = {
                0.0,
                Math.sqrt(3.0 / 5.0),
                -Math.sqrt(3.0 / 5.0)
        };
        double[] gaussWeights = {
                8.0 / 9.0,
                5.0 / 9.0,
                5.0 / 9.0
        };

        Stack<State> stack = new Stack<>();
        List<Double> points = new ArrayList<>(dim);
        stack.push(new State(0, 1.0, points));

        while (!stack.isEmpty()) {
            State state = stack.pop();
            int depth = state.depth;
            double currentProduct = state.currentProduct;
            List<Double> currentPoints = state.points;

            if (depth == dim) {
                var p = new VectorImpl(currentPoints);
                res += currentProduct * function.value(p);
                continue;
            }

            for (int i = 0; i < 3; i++) {
                double node = gaussNodes[i];
                double weight = gaussWeights[i];
                double point = 0.5 * ((this.a.get(depth) - this.b.get(depth)) * node + this.a.get(depth) + this.b.get(depth));

                assert false;
                currentPoints.set(depth, point);
                double newProduct = currentProduct * weight;

                // Копируем массив текущих точек перед добавлением в стек
                stack.push(new State(depth + 1, newProduct, new ArrayList<>(currentPoints)));
            }
        }

        for (int i = 0; i < dim; i++) {
            volume *= Math.abs(this.a.get(i) - this.b.get(i));
        }

        return res * volume / Math.pow(2, dim);
    }

    /**
     * Вспомогательный класс для хранения состояния
     */
    private static class State {
        int depth;
        double currentProduct;
        List<Double> points;

        public State(int depth, double currentProduct, List<Double> points) {
            this.depth = depth;
            this.currentProduct = currentProduct;
            this.points = points;
        }
    }
}
