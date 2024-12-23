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

import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OptimizationProblemsSolverApplication {

    public static void main(String[] args) throws InterruptedException, IllegalClassFormatException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SpringApplication.run(OptimizationProblemsSolverApplication.class, args);
    }
}
