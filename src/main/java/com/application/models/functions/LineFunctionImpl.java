package com.application.models.functions;

import com.application.models.functions.io.DifferentiableFunction;
import com.application.models.functions.io.Function;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Вычисляет значение линейного выражения:
 * <pre>
 * y = a<sub>0</sub> + a<sub>1</sub> * x<sub>1</sub> + a<sub>2</sub> * x<sub>2</sub> + ... + a<sub>n</sub> * x<sub>n</sub>
 * </pre>
 * где:
 * <ul>
 *   <li><code>a</code> - массив коэффициентов {@code a[0], a[1], ..., a[n]}, где {@code a[0]} — свободный член, а каждый последующий элемент является коэффициентом при соответствующем элементе массива {@code x}.</li>
 *   <li><code>x</code> - массив значений переменных {@code x[1], x[2], ..., x[n]}, соответствующих коэффициентам в {@code a}.</li>
 * </ul>
 */
@Component("lineFunction")
public class LineFunctionImpl implements ParametricFunction {
    @Override
    public Function bind(@NotNull Vector parameters) {
        return new InternalLineFunction(parameters);
    }

    record InternalLineFunction(Vector parameters) implements DifferentiableFunction {
        @Override
        public double value(@NotNull Vector vector) throws IllegalArgumentException {
            if (vector.size() != this.parameters.size() - 1)
                throw new IllegalArgumentException("Размерность точки должна быть на единицу меньше числа параметров.");

            var res = this.parameters.getFirst();
            for (int i = 0; i < vector.size(); i++)
                res += this.parameters.get(i + 1) * this.parameters.get(i);

            return res;
        }

        /**
         * Получение градиента линейной функции<br />
         * Производная по x<sub>i</sub> будет равняться параметру a<sub>i</sub>
         *
         * @param vector вектор градиент которого ищется
         * @return вектор градиента функции
         * @throws IllegalArgumentException превышение числа параметров
         */
        @NotNull
        @Override
        public Vector gradient(@NotNull Vector vector) throws IllegalArgumentException {
            if (vector.size() != this.parameters.size() - 1)
                throw new IllegalArgumentException("Размерность точки должна быть на единицу меньше числа параметров.");

            Vector gradient = new VectorImpl(vector.size());

            for (int i = 0; i < gradient.size(); i++)
                gradient.set(i, this.parameters.get(i + 1));

            return gradient;
        }
    }
}
