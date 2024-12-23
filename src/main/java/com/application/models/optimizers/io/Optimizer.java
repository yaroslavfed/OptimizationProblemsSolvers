package com.application.models.optimizers.io;

import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;

import java.lang.instrument.IllegalClassFormatException;

public interface Optimizer<TFunctional extends Functional> {
    /**
     * Оптимизация решения с помощью минимизации функционала
     * @param objective минимизируемый функционал
     * @param function параметрическая функция, функционал которой будем минимизировать
     * @param initialParameters вектор начальных параметров
     * @return вектор результата минимизации
     */
    Vector minimize(TFunctional objective,
                    ParametricFunction function,
                    Vector initialParameters) throws InterruptedException, IllegalClassFormatException;

    /**
     * Оптимизация решения с помощью минимизации функционала
     * @param objective минимизируемый функционал
     * @param function параметрическая функция, функционал которой будем минимизировать
     * @param initialParameters вектор начальных параметров
     * @param minimumParameters вектор минимальных параметров
     * @return вектор результата минимизации
     */
    Vector minimize(TFunctional objective,
                    ParametricFunction function,
                    Vector initialParameters,
                    Vector minimumParameters) throws InterruptedException, IllegalClassFormatException;

    /**
     * Оптимизация решения с помощью минимизации функционала
     * @param objective минимизируемый функционал
     * @param function параметрическая функция, функционал которой будем минимизировать
     * @param initialParameters вектор начальных параметров
     * @param minimumParameters вектор минимальных параметров
     * @param maximumParameters вектор максимальных параметров параметров
     * @return вектор результата минимизации
     */
    Vector minimize(TFunctional objective,
                    ParametricFunction function,
                    Vector initialParameters,
                    Vector minimumParameters,
                    Vector maximumParameters) throws InterruptedException, IllegalClassFormatException;
}
