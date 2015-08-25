package com.donkeigy;

/**
 * Created by cedric on 8/24/15.
 */
import com.donkeigy.services.YahooDataService;
import com.yahoo.engine.YahooFantasyEngine;
import com.yahoo.objects.api.YahooApiInfo;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.utils.oauth.OAuthConnection;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;

@Configuration
@ComponentScan({"com.yahoo.objects.query", "com.yahoo.objects.oauth", "com.yahoo.utils.yql",
        "com.yahoo.utils.oauth", "com.yahoo.dao.implementation",
        "com.yahoo.dao.interfaces", "com.yahoo.services", "com.donkeigy.services", "com.donkeigy.controllers"})

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages={"com.yahoo.objects.query",
                                        "com.yahoo.objects.oauth", "com.yahoo.utils.yql",
                                        "com.yahoo.utils.oauth", "com.yahoo.dao.implementation",
                                        "com.yahoo.dao.interfaces", "com.yahoo.services"})


public class Application extends SpringBootServletInitializer {

    @Autowired
    YahooDataService yahooDataService;


    public static void main(String[] args) {



        System.out.println("Let's inspect the beans provided by Spring Boot:");

        ApplicationContext ctx =  SpringApplication.run(Application.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(Application.class);
    }
}