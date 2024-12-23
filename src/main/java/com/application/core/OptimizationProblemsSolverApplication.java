package com.application.core;

import com.application.core.configs.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.instrument.IllegalClassFormatException;

@SpringBootApplication
public class OptimizationProblemsSolverApplication {

    public static void main(String[] args) throws InterruptedException, IllegalClassFormatException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SpringApplication.run(OptimizationProblemsSolverApplication.class, args);
    }
}
