package com.powerreviews.config;

import com.powerreviews.entities.Config;
import com.powerreviews.repositories.ConfigRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by nadinenack on 9/23/16.
 */
@Component
public class ConfigLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ConfigRepository configRepository;

    private static final Logger logger = Logger.getLogger(ConfigLoader.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Putting stuff in the db");
        Config config = new Config();
        config.setKey("ALLOW_CUSTOMER_IMAGES2");
        config.setValue("false");
        config.setId(1L);
        config.setDescription("");
        config.setEditableBy(Config.AccessLevel.merchant);
        config.setFieldType("boolean");
        config.setOverrideLevel(Config.OverrideLevel.category);
        configRepository.save(config);
        logger.info("Put the stuff in the db");
    }
}
