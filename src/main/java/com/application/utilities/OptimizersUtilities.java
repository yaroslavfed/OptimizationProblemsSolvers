package com.application.utilities;

import com.application.models.functionals.io.DifferentiableFunctional;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import com.application.utilities.operations.VectorOperations;
import org.jetbrains.annotations.NotNull;

public class OptimizersUtilities {
    /**
     * Простой одномерный поиск для определения шага
     */
    public static double lineSearch(DifferentiableFunctional objective, ParametricFunction function, @NotNull Vector point, Vector direction) {
        double alpha = 1;
        var curpoint = new VectorImpl();
        for (int i = 0; i < point.size(); i++)
            curpoint.add(point.get(i) + alpha * direction.get(i));
        var curfunction = function.bind(curpoint);
        var lastValue = 0.0;
        var curvalue = objective.value(curfunction);
        do {
            lastValue = curvalue;
            alpha /= 2;
            curpoint = new VectorImpl();
            for (int i = 0; i < point.size(); i++) {
                curpoint.add(point.get(i) + alpha * direction.get(i));
            }
            curfunction = function.bind(curpoint);
            curvalue = objective.value(curfunction);
        } while (curvalue < lastValue);
        return alpha * 2;
    }

    /**
     * Применение ограничений к вектору параметров
     *
     * @param parameters        исходный вектор
     * @param minimumParameters вектор минимальных парамеров
     * @param maximumParameters вектор максимальных параметров
     * @return ограниченный вектор параметров
     */
    @NotNull
    public static Vector applyConstraints(Vector parameters, Vector minimumParameters, Vector maximumParameters) {
        Vector result = VectorOperations.copyVector(parameters);
        for (int i = 0; i < parameters.size(); i++) {
            if (minimumParameters != null && result.get(i) < minimumParameters.get(i))
                result.set(i, minimumParameters.get(i));
            if (maximumParameters != null && result.get(i) > maximumParameters.get(i))
                result.set(i, maximumParameters.get(i));
        }
        return result;
    }
}
