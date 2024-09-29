package com.solver.optimizers.configs;

import com.solver.optimizers.abstractions.functionals.IFunctional;
import com.solver.optimizers.implementations.MyFunctional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class FunctionalsConfig {
    @Bean(name = "myFunctional")
    public IFunctional myFunctional() {
        return new MyFunctional();
    }
}
