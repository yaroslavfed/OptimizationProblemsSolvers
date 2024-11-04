package com.application.core;

import com.application.dto.enums.OptimizerType;
import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.ParametricFunction;
import com.application.dto.Point;
import com.application.models.optimizers.io.Optimizer;
import com.application.models.vectors.io.Vector;
import com.application.core.configs.AppConfig;
import com.application.services.optimizers.OptimizersService;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class OptimizationProblemsSolverApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SpringApplication.run(OptimizationProblemsSolverApplication.class, args);

        Vector initialVector = context.getBean(Vector.class);
        Functional functional = context.getBean("functional", Functional.class);
        ParametricFunction function = context.getBean("lineFunction", ParametricFunction.class);

        OptimizersService optimizersService = context.getBean(OptimizersService.class);
        Optimizer optimizer = optimizersService.getOptimizer(context, OptimizerType.MONTE_CARLO_METHOD);

        initialVector.add(1.0);
        initialVector.add(1.0);

        ArrayList<Point> points = initializePoints();

        functional.setPoints(points);
        var result = optimizer.minimize(functional, function, initialVector);
        System.out.printf("a = %f, b = %f%n", result.get(0), result.get(1));

    }

    @NotNull
    private static ArrayList<Point> initializePoints() {
        return new ArrayList<>(Arrays.asList(
                new Point(32.56, 78.45),
                new Point(12.34, 56.78),
                new Point(45.67, 89.01),
                new Point(67.89, 12.34),
                new Point(23.45, 67.89),
                new Point(54.32, 23.45),
                new Point(88.88, 99.99),
                new Point(15.15, 84.84),
                new Point(90.90, 55.55),
                new Point(44.44, 11.11),
                new Point(78.12, 36.76),
                new Point(65.54, 32.13),
                new Point(22.22, 44.44),
                new Point(39.99, 78.45),
                new Point(10.10, 25.25),
                new Point(99.99, 1.11),
                new Point(88.11, 55.00),
                new Point(77.77, 33.33),
                new Point(66.66, 99.99),
                new Point(11.12, 33.34),
                new Point(77.78, 44.45)
        ));
    }
}
