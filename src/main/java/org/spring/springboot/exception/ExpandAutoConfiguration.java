package org.spring.springboot.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class ExpandAutoConfiguration {
    public ExpandAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public ExceptionResolver exceptionResolver() {
        return new ExceptionResolver();
    }

    @Bean(
            name = {"jsonView"}
    )
    @ConditionalOnMissingBean
    public MappingJackson2JsonView mappingJackson2JsonView() {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        return jsonView;
    }
}
