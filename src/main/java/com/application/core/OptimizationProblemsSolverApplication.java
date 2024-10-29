package com.application.core;

import com.application.functions.LineFunctionImpl;
import com.application.functions.PolynomialFunctionImpl;
import com.application.functions.io.ParametricFunction;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import com.application.core.configs.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class OptimizationProblemsSolverApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SpringApplication.run(OptimizationProblemsSolverApplication.class, args);

        // Создаем вектор параметров [n, a0, a1] для функции f(x) = 3 + 5 * x
        Vector parameters = new VectorImpl(List.of(3.0));

        // Инициализация линейной функции
        ParametricFunction lineFunc = new LineFunctionImpl();
        // Инициализация полиномиальной функции
        ParametricFunction polynomFunc = new PolynomialFunctionImpl();

        // Привязка параметров (получаем конкретную функцию)
        var lineBoundFunc = lineFunc.bind(parameters);
        var polynomBoundFunc = polynomFunc.bind(parameters);

        // Точки для вычисления значения и градиента
        Vector point1 = new VectorImpl(List.of(4.0));
        Vector point2 = new VectorImpl(List.of(-3.0));
        Vector point3 = new VectorImpl(List.of(10.0));

        // Вычисление значений функции в этих точках
        System.out.println("line");
        System.out.printf("Значение функции в точке 4: %f%n", lineBoundFunc.value(point1));
        System.out.printf("Значение функции в точке -3: %f%n", lineBoundFunc.value(point2));
        System.out.printf("Значение функции в точке 10: %f%n", lineBoundFunc.value(point3));

        // Вычисление значений функции в этих точках
        System.out.println("polynom");
        System.out.printf("Значение функции в точке 4: %f%n", polynomBoundFunc.value(point1));
        System.out.printf("Значение функции в точке -3: %f%n", polynomBoundFunc.value(point2));
        System.out.printf("Значение функции в точке 10: %f%n", polynomBoundFunc.value(point3));

        System.out.println();
    }
}
