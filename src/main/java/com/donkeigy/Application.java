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
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@ComponentScan({"com.yahoo.objects.query", "com.yahoo.objects.oauth", "com.yahoo.utils.yql",
        "com.yahoo.utils.oauth", "com.yahoo.dao.implementation",
        "com.yahoo.dao.interfaces", "com.yahoo.services", "com.donkeigy.services", "com.donkeigy.controllers"})

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages={"com.yahoo.objects.query",
                                        "com.yahoo.objects.oauth", "com.yahoo.utils.yql",
                                        "com.yahoo.utils.oauth", "com.yahoo.dao.implementation",
                                        "com.yahoo.dao.interfaces", "com.yahoo.services"})
@PropertySource({ "file:src/main/resources/Config/database.properties" })

public class Application extends SpringBootServletInitializer {

   // @Autowired
   // YahooDataService yahooDataService;
    @Autowired
    private Environment env;


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
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.yahoo.objects.query","com.yahoo.objects.oauth" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        //sessionFactory.setEntityInterceptor(this.wietHibernateInterceptor);

        return sessionFactory;
    }

    @Bean
    public DataSource restDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver.className"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            private static final long serialVersionUID = 1L;

            {
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                //setProperty("hibernate.globally_quoted_identifiers",
                //        env.getProperty("hibernate.globally_quoted_identifiers"));
                setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
                //setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
            }
        };
    }
}