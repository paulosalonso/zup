package com.github.paulosalonso.zup.application.configuration;

import com.github.paulosalonso.zup.usecase.publisher.Publisher;
import com.github.paulosalonso.zup.usecase.publisher.PublisherImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfiguration {

    @Bean
    public Publisher publisher() {
        return new PublisherImpl();
    }
}
