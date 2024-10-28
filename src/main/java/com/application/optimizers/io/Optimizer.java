package com.application.optimizers.io;

import com.application.functionals.io.Functional;
import com.application.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;

public interface Optimizer {
    /**
     * Получаем максимальное количество итераций
     * @return максимальное количество итераций
     */
    int GetMaxIterations();

    /**
     * Устанавливаем максимальное количество итераций
     * @param maxIterations максимальное количество итераций
     */
    void SetMaxIterations(int maxIterations);

    /**
     * Оптимизация решения с помощью минимизации функционала
     * @param objective минимизируемый функционал
     * @param function параметрическая функция, функционал которой будем минимизировать
     * @param initialParameters вектор начальных параметров
     * @return вектор результата минимизации
     */
    Vector minimize(Functional objective,
                    ParametricFunction function,
                    Vector initialParameters);

    /**
     * Оптимизация решения с помощью минимизации функционала
     * @param objective минимизируемый функционал
     * @param function параметрическая функция, функционал которой будем минимизировать
     * @param initialParameters вектор начальных параметров
     * @param minimumParameters вектор минимальных параметров
     * @return вектор результата минимизации
     */
    Vector minimize(Functional objective,
                    ParametricFunction function,
                    Vector initialParameters,
                    Vector minimumParameters);

    /**
     * Оптимизация решения с помощью минимизации функционала
     * @param objective минимизируемый функционал
     * @param function параметрическая функция, функционал которой будем минимизировать
     * @param initialParameters вектор начальных параметров
     * @param minimumParameters вектор минимальных параметров
     * @param maximumParameters вектор максимальных параметров параметров
     * @return вектор результата минимизации
     */
    Vector minimize(Functional objective,
                    ParametricFunction function,
                    Vector initialParameters,
                    Vector minimumParameters,
                    Vector maximumParameters);
}
