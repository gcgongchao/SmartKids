package com.cmbb.smartkids.network.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cmbb.smartkids.db.SmartKidContract;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "context"
})
public class ResponseListModel {

    // Context是一个数组

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

    @Override
    public String toString() {
        return "ResponseModel{" +
                "code='" + code + '\'' +
                ", context=" + context +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}