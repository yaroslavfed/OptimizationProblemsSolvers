package com.application.core.configs;

import com.application.optimizers.io.Optimizer;
import com.application.optimizers.MonteCarloMethodImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class OptimizersConfig {
    @Bean(name = "monteCarlOptimizer")
    public Optimizer monteCarlOptimizer() {
        return new MonteCarloMethodImpl();
    }
}