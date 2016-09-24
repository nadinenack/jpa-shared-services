package com.powerreviews.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by nadinenack on 9/23/16.
 */
@org.springframework.context.annotation.Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.powerreviews.entities"})
@EnableJpaRepositories(basePackages = {"com.powerreviews.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
