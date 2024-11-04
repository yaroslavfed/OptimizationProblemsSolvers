package com.application.models.functions;

import com.application.models.functions.io.Function;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Вычисляет значение полиномиальной функции:
 * <pre>
 * y = a<sub>0</sub> + a<sub>1</sub> * x + a<sub>2</sub> * x<sup>2</sup> + ... + a<sub>n</sub> * x<sup>n</sup>
 * </pre>
 * где:
 * <ul>
 *   <li><code>a</code> - массив коэффициентов полинома {@code a[0], a[1], ..., a[n]}, где {@code a[i]} соответствует коэффициенту при степени {@code x^i}.</li>
 *   <li><code>x</code> - значение переменной, для которой вычисляется значение полинома.</li>
 * </ul>
 */
@Component("polynomialFunction")
public class PolynomialFunctionImpl implements ParametricFunction {
    @Override
    public Function bind(Vector parameters) {
        return new InternalPolynomialFunction(parameters);
    }

    record InternalPolynomialFunction(Vector parameters) implements Function {
        @Override
        public double value(@NotNull Vector point) throws IllegalArgumentException {
            if (point.size() != 1)
                throw new IllegalArgumentException("Рвзмерность точки должна быть равна 1");

            var result = parameters.getFirst();

            for (int i = 1; i < parameters.size(); i++)
                result += parameters.get(i) * Math.pow(point.getFirst(), i);

            return result;
        }
    }
}
