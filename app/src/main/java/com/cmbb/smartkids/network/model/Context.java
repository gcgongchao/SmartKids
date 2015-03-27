package com.cmbb.smartkids.network.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "presentation",
        "token",
        "HomeSameAge"
})
public class Context {

    @JsonProperty("presentation")
    private String presentation;
    @JsonProperty("token")
    private String token;
    @JsonProperty("HomeSameAge")
    private HomeSameAge[] HomeSameAge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The presentation
     */
    @JsonProperty("presentation")
    public String getPresentation() {
        return presentation;
    }

    /**
     *
     * @param presentation
     * The presentation
     */
    @JsonProperty("presentation")
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    /**
     *
     * @return
     * The token
     */
    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The HomeSameAge
     */
    @JsonProperty("HomeSameAge")
    public HomeSameAge[] getHomeSameAge() {
        return HomeSameAge;
    }

    /**
     *
     * @param HomeSameAge
     * The HomeSameAge
     */
    @JsonProperty("HomeSameAge")
    public void setHomeSameAge(HomeSameAge[] HomeSameAge) {
        this.HomeSameAge = HomeSameAge;
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