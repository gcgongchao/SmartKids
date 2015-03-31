package com.cmbb.smartkids.network.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N.Sun
 */
public class ResponseWithListModel {

    // Context是包含一个数组
    @JsonProperty("code")
    private String code;
    @JsonProperty("context")
    private ArrayList<HomeSameAge> context;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The code
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The context
     */
    @JsonProperty("context")
    public ArrayList<HomeSameAge> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    @JsonProperty("context")
    public void setContext(ArrayList<HomeSameAge> context) {
        this.context = context;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
