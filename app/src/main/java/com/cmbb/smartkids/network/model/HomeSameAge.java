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
        "bigImg",
        "bigImgHeight",
        "bigImgWidth",
        "connector",
        "context",
        "count",
        "id",
        "smallImg",
        "smallImgHeight",
        "smallImgWidth",
        "title",
        "type"
})
public class HomeSameAge {

    @JsonProperty("bigImg")
    private String bigImg;
    @JsonProperty("bigImgHeight")
    private Object bigImgHeight;
    @JsonProperty("bigImgWidth")
    private Object bigImgWidth;
    @JsonProperty("connector")
    private String connector;
    @JsonProperty("context")
    private String context;
    @JsonProperty("count")
    private Object count;
    @JsonProperty("id")
    private Object id;
    @JsonProperty("smallImg")
    private String smallImg;
    @JsonProperty("smallImgHeight")
    private Object smallImgHeight;
    @JsonProperty("smallImgWidth")
    private Object smallImgWidth;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The bigImg
     */
    @JsonProperty("bigImg")
    public String getBigImg() {
        return bigImg;
    }

    /**
     *
     * @param bigImg
     * The bigImg
     */
    @JsonProperty("bigImg")
    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    /**
     *
     * @return
     * The bigImgHeight
     */
    @JsonProperty("bigImgHeight")
    public Object getBigImgHeight() {
        return bigImgHeight;
    }

    /**
     *
     * @param bigImgHeight
     * The bigImgHeight
     */
    @JsonProperty("bigImgHeight")
    public void setBigImgHeight(Object bigImgHeight) {
        this.bigImgHeight = bigImgHeight;
    }

    /**
     *
     * @return
     * The bigImgWidth
     */
    @JsonProperty("bigImgWidth")
    public Object getBigImgWidth() {
        return bigImgWidth;
    }

    /**
     *
     * @param bigImgWidth
     * The bigImgWidth
     */
    @JsonProperty("bigImgWidth")
    public void setBigImgWidth(Object bigImgWidth) {
        this.bigImgWidth = bigImgWidth;
    }

    /**
     *
     * @return
     * The connector
     */
    @JsonProperty("connector")
    public String getConnector() {
        return connector;
    }

    /**
     *
     * @param connector
     * The connector
     */
    @JsonProperty("connector")
    public void setConnector(String connector) {
        this.connector = connector;
    }

    /**
     *
     * @return
     * The context
     */
    @JsonProperty("context")
    public String getContext() {
        return context;
    }

    /**
     *
     * @param context
     * The context
     */
    @JsonProperty("context")
    public void setContext(String context) {
        this.context = context;
    }

    /**
     *
     * @return
     * The count
     */
    @JsonProperty("count")
    public Object getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    @JsonProperty("count")
    public void setCount(Object count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(Object id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The smallImg
     */
    @JsonProperty("smallImg")
    public String getSmallImg() {
        return smallImg;
    }

    /**
     *
     * @param smallImg
     * The smallImg
     */
    @JsonProperty("smallImg")
    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    /**
     *
     * @return
     * The smallImgHeight
     */
    @JsonProperty("smallImgHeight")
    public Object getSmallImgHeight() {
        return smallImgHeight;
    }

    /**
     *
     * @param smallImgHeight
     * The smallImgHeight
     */
    @JsonProperty("smallImgHeight")
    public void setSmallImgHeight(Object smallImgHeight) {
        this.smallImgHeight = smallImgHeight;
    }

    /**
     *
     * @return
     * The smallImgWidth
     */
    @JsonProperty("smallImgWidth")
    public Object getSmallImgWidth() {
        return smallImgWidth;
    }

    /**
     *
     * @param smallImgWidth
     * The smallImgWidth
     */
    @JsonProperty("smallImgWidth")
    public void setSmallImgWidth(Object smallImgWidth) {
        this.smallImgWidth = smallImgWidth;
    }

    /**
     *
     * @return
     * The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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