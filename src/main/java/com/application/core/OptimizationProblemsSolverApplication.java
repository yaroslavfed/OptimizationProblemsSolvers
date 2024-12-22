package com.application.core;

import com.application.enums.LoggerStatus;
import com.application.enums.OptimizerType;
import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.ParametricFunction;
import com.application.data.Point;
import com.application.models.optimizers.io.Optimizer;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import com.application.core.configs.AppConfig;
import com.application.services.logger.Logger;
import com.application.services.optimizers.OptimizersService;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OptimizationProblemsSolverApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SpringApplication.run(OptimizationProblemsSolverApplication.class, args);
        Logger logger = context.getBean(Logger.class);

        Functional functional = context.getBean("functional", Functional.class);
        ParametricFunction function = context.getBean("polynomialFunction", ParametricFunction.class);

        OptimizersService optimizersService = context.getBean(OptimizersService.class);
        Optimizer optimizer = optimizersService.getOptimizer(context, OptimizerType.MONTE_CARLO_METHOD);

        Vector initialParameters = new VectorImpl(List.of(0.0));
        Vector minimumParameters = new VectorImpl(List.of(-10.0));
        Vector maximumParameters = new VectorImpl(List.of(10.0));

        ArrayList<Point> points = initializePoints();

        functional.setPoints(points);
        var result = optimizer.minimize(functional, function, initialParameters, minimumParameters, maximumParameters);

        System.out.println();
        logger.log(LoggerStatus.TRACE, String.format("result:\t\t%s", result));
    }

    @NotNull
    private static ArrayList<Point> initializePoints() {
        return new ArrayList<>(Arrays.asList(
                new Point(-1, 21),
                new Point(1.5, 7.25),
                new Point(3, 5),
                new Point(7, 21)
        ));
    }
}
