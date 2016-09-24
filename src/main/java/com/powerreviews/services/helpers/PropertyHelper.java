package com.powerreviews.services.helpers;

import com.powerreviews.controllers.pojos.AggregatedProperty;
import com.powerreviews.entities.Config;
import com.powerreviews.services.PropertyConfigService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Created by nadinenack on 9/24/16.
 */
@Component
public class PropertyHelper {

    @Resource
    private PropertyConfigService configService;

    private EmailValidator emailValidator = EmailValidator.getInstance();

    public Optional<String> verifyDataType(final AggregatedProperty aggregatedProperty, final Config config) {
        if(StringUtils.equals(config.getFieldType(), Config.FIELD_TYPE_TEXT)
                || StringUtils.equals(config.getFieldType(), Config.FIELD_TYPE_TEXTAREA)) {
            if(aggregatedProperty.getValue() == null) {
                return Optional.of(prepareErrorMessage(null, config.getFieldType()));
            }
        }else if(StringUtils.equals(config.getFieldType(), Config.FIELD_TYPE_BOOLEAN)) {
            if(!StringUtils.equals(aggregatedProperty.getValue().toLowerCase(), "false")
                    && !Boolean.valueOf(aggregatedProperty.getValue())) {
                return Optional.of(prepareErrorMessage(aggregatedProperty.getValue(), config.getFieldType()));
            }
        }else if(StringUtils.equals(config.getFieldType(), Config.FIELD_TYPE_EMAIL)) {
            if(!emailValidator.isValid(aggregatedProperty.getValue())) {
                return Optional.of(prepareErrorMessage(aggregatedProperty.getValue(), config.getFieldType()));
            }
        }else if(StringUtils.equals(config.getFieldType(), Config.FIELD_TYPE_WEEKLY_RECCURING)) {
            if(aggregatedProperty.getValue() == null) {
                return Optional.of(prepareErrorMessage(null, config.getFieldType()));
            }
        }else if(StringUtils.startsWith(config.getFieldType(), "com.pufferfish")) {
            //Pufferfish enumeration
            List<String> possibleValues = configService.getPossibleValues(config);
            if(possibleValues == null || possibleValues.isEmpty()) {
                throw new IllegalStateException("Unable to locate config value");
            }
            if(!possibleValues.contains(aggregatedProperty.getValue())) {
                return Optional.of(prepareErrorMessage(aggregatedProperty.getValue(), config.getFieldType()));
            }
        }else {
            return Optional.of(String.format("Unrecognized property config data type %s for key %s",
                    config.getFieldType(), config.getKey()));
        }
        return Optional.empty();
    }

    String prepareErrorMessage(final String value, final String dataType) {
        return String.format("%s is not a valid value for %s data type", value, dataType);
    }

}
