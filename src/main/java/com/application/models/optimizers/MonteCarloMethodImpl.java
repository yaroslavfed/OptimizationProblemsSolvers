package com.application.models.optimizers;

import com.application.enums.LoggerStatus;
import com.application.models.optimizers.io.Optimizer;
import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import com.application.models.vectors.VectorImpl;
import com.application.services.logger.Logger;
import com.application.services.painter.PainterService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * <p>Универсальный метод оптимизации</p>
 * Метод имитации отжига
 */
@Service("monteCarloMethod")
public class MonteCarloMethodImpl implements Optimizer {
    private static final int MAX_ITERS = 1000000;
    private static final double TEMPERATURE = 1000.0;
    private static final double COOLING_RATE = (1 - 1e-3);

    private final Logger logger;
    private final PainterService painterService;

    public MonteCarloMethodImpl(@Qualifier("consoleLogger") Logger logger,
                                @Qualifier("consolePainter") PainterService painterService) {
        this.logger = logger;
        this.painterService = painterService;
    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters) throws InterruptedException {
        return minimize(objective, function, initialParameters, new VectorImpl(), new VectorImpl());
    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters, Vector minimumParameters) throws InterruptedException {
        return minimize(objective, function, initialParameters, minimumParameters, new VectorImpl());
    }

    @Override
    public Vector minimize(@NotNull Functional functional,
                           @NotNull ParametricFunction function,
                           Vector initialParameters,
                           Vector minimumParameters,
                           Vector maximumParameters) throws InterruptedException {
        double currentTemperature = TEMPERATURE;
        Vector currentParameters = new VectorImpl(initialParameters);
        double bestFunctionalValue = functional.value(function.bind(currentParameters));

        var minValue = Collections.min(minimumParameters);
        var maxValue = Collections.max(maximumParameters);

        int currentIteration = 0;
        // Останавливаемся если температура упала до критической или достигли максимального количества итераций
        while (currentTemperature > 1e-3 && currentIteration < MAX_ITERS) {
            Vector newParameters = generateNeighbor(currentParameters, minimumParameters, maximumParameters, currentTemperature);
            double newFunctionalValue = functional.value(function.bind(newParameters));

            if (acceptanceProbability(bestFunctionalValue, newFunctionalValue, currentTemperature) > Math.random()) {
                currentParameters = new VectorImpl(newParameters);
                bestFunctionalValue = newFunctionalValue;
            }

            // Снижение температуры
            currentTemperature *= COOLING_RATE;

            if (!(minimumParameters.isEmpty() || maximumParameters.isEmpty())) {
                painterService.paint((int) (maxValue - minValue), currentParameters);
            } else {
                System.out.println();
                logger.log(LoggerStatus.TRACE, "iteration:\t" + currentIteration);
                logger.log(LoggerStatus.TRACE, "temperature:\t" + currentTemperature);
                logger.log(LoggerStatus.TRACE, "parameters:\t" + currentParameters);
            }

            currentIteration++;
        }

        return currentParameters;
    }

    /**
     * Генерация соседнего состояния, ограниченного минимумом и максимумом параметров
     *
     * @param parameters        исходные параметры
     * @param minimumParameters нижняя граница
     * @param maximumParameters верхняя граница
     * @return новый вектор параметров
     */
    @NotNull
    private Vector generateNeighbor(@NotNull Vector parameters, Vector minimumParameters, Vector maximumParameters, double currentTemperature) {
        Vector neighborSolution = new VectorImpl();
        for (int i = 0; i < parameters.size(); i++) {
            double initialStepSize = (maximumParameters.get(i) - minimumParameters.get(i)) / 10;
            var stepSize = updateStepSize(initialStepSize, currentTemperature);

            //FIXME Логирование (убрано для отображения графика сходимости)
//            logger.log(LoggerStatus.INFO, "stepSize:\t" + stepSize);

            double randomShift = (Math.random() - 0.5) * 2 * stepSize;
            double newValue = parameters.get(i) + randomShift;

            //FIXME Логирование (убрано для отображения графика сходимости)
//            logger.log(LoggerStatus.INFO, "newValue:\t" + newValue);

            // Применяем ограничение
            newValue = Math.max(minimumParameters.get(i), Math.min(newValue, maximumParameters.get(i)));

            neighborSolution.add(newValue);
        }
        return neighborSolution;
    }

    /**
     * Обновление шага смещения в зависимости от текущей температуры
     *
     * @param initialStepSize    начальное значение шага смещения
     * @param currentTemperature текущая температура
     * @return новое значение шага смещения
     */
    private double updateStepSize(double initialStepSize, double currentTemperature) {
        return initialStepSize * (currentTemperature / MonteCarloMethodImpl.TEMPERATURE);
    }

    /**
     * Oпределения вероятности принятия нового решения на основе его оценки по сравнению с текущим решением
     *
     * @param bestFunctionalValue лучшее значение функционала
     * @param newFunctionalValue  новое значение функционала
     * @param temperature         текушая температура
     * @return вероятность принятия нового решения
     */
    private double acceptanceProbability(double bestFunctionalValue, double newFunctionalValue, double temperature) {
        return (newFunctionalValue < bestFunctionalValue)
                ? 1.0
                : Math.exp(-(newFunctionalValue - bestFunctionalValue) / temperature);
    }
}
