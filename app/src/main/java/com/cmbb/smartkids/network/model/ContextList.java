package com.cmbb.smartkids.network.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N.Sun
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "attention",
        "count",
        "list"
})
public class ContextList {
    @JsonProperty("attention")
    private String presentation;
    @JsonProperty("count")
    private String token;
    @JsonProperty("list")
    private ArrayList<HomeSameAgeList> homeSameAgeList;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The presentation
     */
    @JsonProperty("attention")
    public String getPresentation() {
        return presentation;
    }

    /**
     * @param presentation The presentation
     */
    @JsonProperty("attention")
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    /**
     * @return The token
     */
    @JsonProperty("count")
    public String getToken() {
        return token;
    }

    /**
     * @param token The token
     */
    @JsonProperty("count")
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return The HomeSameAge
     */
    @JsonProperty("list")
    public ArrayList<HomeSameAgeList> getHomeSameAge() {
        return homeSameAgeList;
    }

    /**
     * @param HomeSameAge The HomeSameAge
     */
    @JsonProperty("list")
    public void setHomeSameAge(ArrayList<HomeSameAgeList> HomeSameAge) {
        this.homeSameAgeList = HomeSameAge;
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
