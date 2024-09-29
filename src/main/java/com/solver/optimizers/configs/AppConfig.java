package com.solver.optimizers.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({ModelsConfig.class,
        FunctionsConfig.class,
        FunctionalsConfig.class,
        OptimizersConfig.class})
public class AppConfig {
}
