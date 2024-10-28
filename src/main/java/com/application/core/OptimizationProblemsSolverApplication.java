package com.application.core;

import com.application.functionals.io.Functional;
import com.application.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import com.application.optimizers.io.Optimizer;
import com.application.core.configs.AppConfig;
import com.application.models.Point;
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

        Optimizer monteCarlOptimizer = context.getBean("monteCarlOptimizer", Optimizer.class);
        Vector initialVector = context.getBean(Vector.class);
        Functional functional = context.getBean("myFunctional", Functional.class);
        ParametricFunction function = context.getBean("lineFunction", ParametricFunction.class);

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
