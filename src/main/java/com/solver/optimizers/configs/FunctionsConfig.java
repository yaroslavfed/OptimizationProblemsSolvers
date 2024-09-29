package com.solver.optimizers.configs;

import com.solver.optimizers.abstractions.functions.IParametricFunction;
import com.solver.optimizers.implementations.LineFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class FunctionsConfig {
    @Bean(name = "lineFunction")
    public IParametricFunction lineFunction() {
        return new LineFunction();
    }
}
