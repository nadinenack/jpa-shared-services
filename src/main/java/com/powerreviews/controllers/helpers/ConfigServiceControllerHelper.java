package com.powerreviews.controllers.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nadinenack on 9/24/16.
 */
@Component
public class ConfigServiceControllerHelper {


    private ObjectMapper mapper = new ObjectMapper();
    private static final String MESSAGE_KEY = "error";

    private static final Logger logger = Logger.getLogger(ConfigServiceControllerHelper.class);

    @PostConstruct
    public void setupMapper() {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    /**
     * Parses a comma seperated list of one or more strings into a set of strings. Returns an empty set if the string
     * is empty
     * @param keysString
     * @return
     */
    public static Set<String> parseStringToSet(String keysString) {
        if(keysString == null || keysString.trim().isEmpty()) {
            return new HashSet<>();
        }
        String[] keys = keysString.split(",");
        if(keys.length == 0) {
            return new HashSet<>();
        }
        return Arrays.stream(keys).collect(Collectors.toSet());
    }

    /**
     * Turns given error message string into a json object. The key for the error message value is
     * @param error
     * @return
     */
    public String createErrorJson(String error) {
        try {
            return mapper.writeValueAsString(createErrorMap(error));
        } catch (JsonProcessingException e) {
            logger.error(e);
            return "Unexpected error";
        }
    }

    public Map<String, String> createErrorMap(String error) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put(MESSAGE_KEY, error);
        return errorMessage;
    }
}
