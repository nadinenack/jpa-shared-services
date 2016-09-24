package com.powerreviews.services;

import com.powerreviews.entities.Config;
import com.powerreviews.repositories.ConfigRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by nadinenack on 9/24/16.
 */
@Service
public class PropertyConfigService {

    @Resource
    private ConfigRepository repository;

    private static final Logger logger = Logger.getLogger(PropertyConfigService.class);

    /**
     * Gets a Config with specified key
     * @param key
     * @return
     */
    public Config getConfigValue(final String key) {
        return repository.findByKey(key);
    }

    /**
     * Gets a list of Configs with specified keys
     * @param keys
     * @return
     */
    public List<Config> getConfigValues(Set<String> keys) {
        List<Config> configs = repository.findByKeyIn(keys);
        return configs;
    }

    /**
     * This method uses reflection to get the possible values for a Pufferfish enumeration. If the Config data_type
     * is not a Pufferfish enumeration, either because it's of a non-enum datatype (eg. boolean) or we're unable to
     * locate the Pufferfish enumeration, an empty list is returned.
     *
     * @param property
     * @return
     */
    public List<String> getPossibleValues(final Config property) {
        List<String> possibleValues = new ArrayList<>();
        if(!(Config.FIELD_TYPE_BOOLEAN.equals(property.getFieldType()))
                && !(Config.FIELD_TYPE_EMAIL.equals(property.getFieldType()))
                && !(Config.FIELD_TYPE_TEXT.equals(property.getFieldType()))
                && !(Config.FIELD_TYPE_TEXTAREA.equals(property.getFieldType()))
                && !(Config.FIELD_TYPE_WEEKLY_RECCURING.equals(property.getFieldType()))
                ) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("Getting possible values for enum of type %s", property.getFieldType()));
            }

            Class c = null;
            try {
                c = Class.forName(property.getFieldType());
            } catch (ClassNotFoundException e) {
                logger.warn(String.format("Possible bad value in the config table. Unable to locate class with " +
                                "name %s on config table id %d",
                        property.getFieldType(),
                        property.getId()), e);
                return possibleValues;
            }
            Object[] enumConstants = c.getEnumConstants();
            for(Object enumConstant : enumConstants) {
                possibleValues.add(String.valueOf(enumConstant));
            }
        }
        return possibleValues;
    }

}

