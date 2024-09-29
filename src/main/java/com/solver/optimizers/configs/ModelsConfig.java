package com.solver.optimizers.configs;

import com.solver.optimizers.abstractions.models.IVector;
import com.solver.optimizers.implementations.Vector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ModelsConfig {
    @Bean
    public IVector vector() {
        return new Vector();
    }
}
