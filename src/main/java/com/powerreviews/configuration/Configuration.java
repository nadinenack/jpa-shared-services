package com.powerreviews.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * Created by nadinenack on 9/23/16.
 */
public class Configuration {
    @Autowired()
    @Qualifier("postgresqlDataSource")
    private DataSource datasource;

    @Bean
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(datasource)
                .packages("sharedservices/entities")
                .persistenceUnit("user-services")
                .build();
    }
}
