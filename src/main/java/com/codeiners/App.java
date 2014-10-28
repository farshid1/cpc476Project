package com.codeiners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableMongoRepositories
//@PropertySource("application.properties")
public class App 
{
    public static void main( String[] args )
    {

        SpringApplication.run(App.class);
    }
}
