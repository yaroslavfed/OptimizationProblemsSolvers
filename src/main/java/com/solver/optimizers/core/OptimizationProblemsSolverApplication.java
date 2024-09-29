package com.solver.optimizers.core;

import com.solver.optimizers.abstractions.functionals.IFunctional;
import com.solver.optimizers.abstractions.functions.IFunction;
import com.solver.optimizers.abstractions.functions.IParametricFunction;
import com.solver.optimizers.domain.Point;
import com.solver.optimizers.abstractions.models.IVector;
import com.solver.optimizers.abstractions.optimizators.IOptimizer;
import com.solver.optimizers.configs.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class OptimizationProblemsSolverApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SpringApplication.run(OptimizationProblemsSolverApplication.class, args);

        IOptimizer monteCarlOptimizer = context.getBean("monteCarlOptimizer", IOptimizer.class);
        IVector initialVector = context.getBean(IVector.class);
        IFunctional functional = context.getBean("myFunctional", IFunctional.class);
        IParametricFunction function = context.getBean("lineFunction", IParametricFunction.class);

        initialVector.add(1.0);
        initialVector.add(1.0);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите n:");
        var n = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Введите точку:");
            var str = scanner.nextLine();
            var coordinates = str.split(" ");

            var point = new Point(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
            points.add(point);
        }
        scanner.close();
        functional.setPoints(points);

        var result = monteCarlOptimizer.minimize(functional, function, initialVector);
        System.out.printf("a = %f, b = %f%n", result.get(0), result.get(1));
    }
}
