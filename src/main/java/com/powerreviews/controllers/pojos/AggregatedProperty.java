package com.powerreviews.controllers.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadinenack on 9/24/16.
 */
public class AggregatedProperty {

    private String key;
    private String value;
    @JsonProperty("data_type")
    private String dataType;
    @JsonProperty("possible_values")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> possibleValues = new ArrayList<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public List<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

}
